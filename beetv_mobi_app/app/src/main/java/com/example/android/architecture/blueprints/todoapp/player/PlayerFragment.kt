package com.example.android.architecture.blueprints.todoapp.player

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.android.architecture.blueprints.todoapp.base.BaseFragment
import com.example.android.architecture.blueprints.todoapp.data.Movie
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentPlayerBinding
import com.example.android.architecture.blueprints.todoapp.movie_detail.MovieDetailFragmentArgs
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory
import com.google.android.exoplayer2.C

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.drm.DrmSessionManager
import com.google.android.exoplayer2.drm.ExoMediaCrypto
import com.google.android.exoplayer2.ext.rtmp.RtmpDataSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util


class PlayerFragment : BaseFragment() {

    val TAG = "PlayerFragment"
    enum class MediaPlayerType {
        Live, TsMovie
    }
    private val args: PlayerFragmentArgs by navArgs()
    private var mPlayerType: MediaPlayerType = MediaPlayerType.TsMovie
    private val viewModel by viewModels<PlayerViewModel> { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentPlayerBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentPlayerBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun setupGUI() {
         val movie = Movie.findMovieById(args.movieID)
        Log.d(TAG, movie?.name)

        if (movie?.id == "0") mPlayerType = MediaPlayerType.Live
    }

    override fun onStart() {
        super.onStart()
        setupGUI()
        initializePlayer()
    }

    override fun onResume() {
        super.onResume()
//        releasePlayer()
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
    }


    private var player: SimpleExoPlayer? = null
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0
    var liveLink = "rtmp://162.211.126.179/live/1016"
    var tsLink = "https://beetv.s3.ap-northeast-2.amazonaws.com/A.Dirty.Carnival.2006.720p.BluRay.x264.AAC-%5BYTS.MX.ts"

    private fun initializePlayer() {
//        val uri = Uri.parse(mp4Link)
        val drmSessionManager: DrmSessionManager<ExoMediaCrypto> = DrmSessionManager.getDummyDrmSessionManager()
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context, Util.getUserAgent(context!!, "BeeTv"))
        val mediaSource: MediaSource
        mediaSource =
//                if (type == C.TYPE_DASH) {
//            DashMediaSource.Factory(dataSourceFactory)
//                    .setDrmSessionManager(drmSessionManager)
//                    .createMediaSource(uri)
//        } else
        if (mPlayerType == MediaPlayerType.Live) {
            ProgressiveMediaSource.Factory(RtmpDataSourceFactory())
            .setDrmSessionManager(drmSessionManager)
            .createMediaSource(Uri.parse(liveLink))
        }

        else {
            ProgressiveMediaSource.Factory(dataSourceFactory)
            .setDrmSessionManager(drmSessionManager)
            .createMediaSource(Uri.parse(tsLink))
        }

        val player = SimpleExoPlayer.Builder(context!!).build()
        viewDataBinding.player.player = player
        player.repeatMode = Player.REPEAT_MODE_ALL
        player.prepare(mediaSource)
        player.playWhenReady = true
        this.player = player
    }

    private fun releasePlayer() {
        viewDataBinding.player.player = null
        if (player != null) {
            player!!.release()
            player = null
        }
    }
}