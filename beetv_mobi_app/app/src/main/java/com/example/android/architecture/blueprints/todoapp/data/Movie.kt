package com.example.android.architecture.blueprints.todoapp.data

import com.google.gson.annotations.SerializedName
import java.util.*

data class Movie(
        val id: String,
        val name: String,
        val director: String,
        val score: Float,
        @SerializedName("cover_image")
        val coverImage: String
) {

    companion object {
        fun mocks() = mutableListOf<Movie>(
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg")
                ,
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg")
                , Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                "tesst avvd", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),

                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg")

        )
    }
}