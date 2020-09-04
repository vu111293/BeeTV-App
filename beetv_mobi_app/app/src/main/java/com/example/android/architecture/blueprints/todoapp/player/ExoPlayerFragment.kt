package com.example.android.architecture.blueprints.todoapp.player

import android.net.Uri
import android.os.Bundle
import androidx.leanback.app.VideoSupportFragment
import androidx.leanback.app.VideoSupportFragmentGlueHost
import androidx.leanback.media.PlaybackTransportControlGlue
import com.example.android.architecture.blueprints.todoapp.data.Movie
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.ext.leanback.LeanbackPlayerAdapter
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.util.Util


class ExoPlayerFragment: VideoSupportFragment() {

    companion object {
        const val UPDATE_DELAY = 16
    }

//    private var tsLink = "https://beetv.s3.ap-northeast-2.amazonaws.com/A.Dirty.Carnival.2006.720p.BluRay.x264.AAC-%5BYTS.MX.ts";
//    private var tsLink = "https://beetv.s3.ap-northeast-2.amazonaws.com/20140122_132744-1920x1080p30.ts";
    private var tsLink = "rtmp://162.211.126.179/live/1016";
    private lateinit var video: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupData()
        setupPlayer()
    }

    private fun setupData() {
//        val videoRepository = VideoRepository()
//        video = videoRepository.getVideoList()[0]
        video = Movie.mocks().first()
    }

    private fun setupPlayer() {
        val bandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val exoPlayer = activity?.let { ExoPlayerFactory.newSimpleInstance(it, trackSelector) }
        val playerAdapter = activity?.let { exoPlayer?.let { it1 -> LeanbackPlayerAdapter(it, it1, UPDATE_DELAY) } }

        val transportControlGlue = PlaybackTransportControlGlue(activity, playerAdapter)
        transportControlGlue.host = VideoSupportFragmentGlueHost(this)
        transportControlGlue.title = video.title
        transportControlGlue.subtitle = video.description
        transportControlGlue.playWhenPrepared()

        val rtmpDataSourceFactory = RtmpDataSourceFactory()

        val mediaSourceUri = Uri.parse(tsLink)
        val userAgent = activity?.let { Util.getUserAgent(it, "VideoPlayerGlue") }
//        val mediaSource = ExtractorMediaSource(
//                mediaSourceUri,
//                rtmpDataSourceFactory,
////                DefaultDataSourceFactory(activity!!, userAgent),
//                DefaultExtractorsFactory(),
//                null,
//                null)

        val mediaSource: MediaSource = ExtractorMediaSource.Factory(rtmpDataSourceFactory)
                .createMediaSource(Uri.parse(tsLink))
        exoPlayer?.prepare(mediaSource)
    }

}