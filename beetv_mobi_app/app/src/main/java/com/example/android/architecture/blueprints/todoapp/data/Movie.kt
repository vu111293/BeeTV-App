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
                Movie("10000", "태양의 후예 다시보기",
                        "태양의 후예 다시보기", "", "태양의 후예 다시보기", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie("10001", "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie("10002", "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie("10003", "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie("10004", "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie("10005", "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg"),
                Movie("10006", "태양의 후예 다시보기",
                        "tesst avvd", "url", "name", "Mozaa", 5.0f, "https://img.mbn.co.kr/filewww/news/other/2016/03/11/201160100101.jpg")
        )

        fun mocks1() = mutableListOf<Movie>(
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "우아한 가", "Mozaa", 5.0f,
                        "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/18/htm_2019101872244519306.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "우아한 가", "Mozaa", 5.0f,
                        "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/18/htm_2019101872244519306.jpg"), Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "우아한 가", "Mozaa", 5.0f,
                "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/18/htm_2019101872244519306.jpg"), Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "우아한 가", "Mozaa", 5.0f,
                "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/18/htm_2019101872244519306.jpg"), Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "우아한 가", "Mozaa", 5.0f,
                "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/18/htm_2019101872244519306.jpg"), Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "우아한 가", "Mozaa", 5.0f,
                "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/18/htm_2019101872244519306.jpg"), Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "우아한 가", "Mozaa", 5.0f,
                "https://pds.joins.com/news/component/htmlphoto_mmdata/201910/18/htm_2019101872244519306.jpg")
        )

        fun mocks2() = mutableListOf<Movie>(
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "마녀보감", "Mozaa", 5.0f,
                        "https://pds.joins.com/news/component/newsis/201607/08/NISI20160513_0011687146_web.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "마녀보감", "Mozaa", 5.0f,
                        "https://pds.joins.com/news/component/newsis/201607/08/NISI20160513_0011687146_web.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "마녀보감", "Mozaa", 5.0f,
                        "https://pds.joins.com/news/component/newsis/201607/08/NISI20160513_0011687146_web.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "마녀보감", "Mozaa", 5.0f,
                        "https://pds.joins.com/news/component/newsis/201607/08/NISI20160513_0011687146_web.jpg"),
                Movie(UUID.randomUUID().toString(), "태양의 후예 다시보기",
                        "tesst avvd", "http://commondatastorage.googleapis.com/android-tv/Sample%20videos/Zeitgeist/Zeitgeist%202010_%20Year%20in%20Review.mp4", "마녀보감", "Mozaa", 5.0f,
                        "https://pds.joins.com/news/component/newsis/201607/08/NISI20160513_0011687146_web.jpg")

        )


        public fun findMovieById(id: String): Movie? {
            return mocks().find { it.id == id }
        }
    }
}