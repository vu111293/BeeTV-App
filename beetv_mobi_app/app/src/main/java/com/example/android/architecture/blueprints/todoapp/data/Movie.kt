package com.example.android.architecture.blueprints.todoapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Movie(
        val id: String,
        val title: String,
        val description: String,
        val videoUrl: String,
        val name: String,
        val director: String,
        val score: Float,
        @SerializedName("cover_image")
        val coverImage: String
) : Serializable {

    companion object {
        fun mocks() = mutableListOf<Movie>(
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg")
        )
    }
}