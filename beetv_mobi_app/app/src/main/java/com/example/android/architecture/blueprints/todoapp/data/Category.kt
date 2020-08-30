package com.example.android.architecture.blueprints.todoapp.data

import com.example.android.architecture.blueprints.todoapp.util.Constants

data class Category(
        val id: String,
        val name: String,
        val content : String= "",
        val channel : String = "",
        val time: String = ""


) {

    companion object {
        fun mocks() = mutableListOf<Category>(
                Category("1",
                        "지상파"),
                Category("2",
                        "종편/뉴스"),
                Category("3",
                        "오락/음악"),
                Category("4",
                        "영화/시리즈"),
                Category("5",
                        "레저/다큐"),
                Category("6",
                "스포츠"),
                Category("7",
                "지상파")
                , Category("8",
                "지상파")
                , Category("9",
                "지상파")
                ,
                Category("10",
                        "지상파"), Category("17",
                "지상파"),
                Category("11",
                        "지상파"),
                Category("12",
                        "지상파"),
                Category("13",
                        "지상파"), Category("14",
                "지상파"),
                Category("15",
                "지상파"), Category("16",
                "지상파")
        )

        fun mocksChannel() = mutableListOf<Category>(
                Category("001",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("002",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("003",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("004",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("005",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("006",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("007",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("008",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("009",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("010",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("011",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("012",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("013",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("014",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("015",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("016",
                        "MBC", "뷰티 인사이드", "SBS"),
                Category("017",
                        "MBC", "뷰티 인사이드", "SBS")
        )

        fun mocksProgram()= mutableListOf<Category>(
                Category("001",
                        "얼마예요?", time = "11:20"),
                Category("002",
                        "얼마예요?", time = "11:20"),
                Category("003",
                        "얼마예요?", time = "11:20"),
                Category("004",
                        "얼마예요?", time = "11:20"),
                Category("005",
                        "얼마예요?", time = "11:20"),
                Category("006",
                        "얼마예요?", time = "11:20"),
                Category("007",
                        "얼마예요?", time = "11:20"),
                Category("008",
                        "얼마예요?", time = "11:20"),
                Category("009",
                        "얼마예요?", time = "11:20"),
                Category("010",
                        "얼마예요?", time = "11:20"),
                Category("011",
                        "얼마예요?", time = "11:20"),
                Category("012",
                        "얼마예요?", time = "11:20"),
                Category("013",
                        "얼마예요?", time = "11:20"),
                Category("014",
                        "얼마예요?", time = "11:20"),
                Category("015",
                        "얼마예요?", time = "11:20"),
                Category("016",
                        "얼마예요?", time = "11:20"),
                Category("017",
                        "얼마예요?", time = "11:20")
        )

        fun mocksChapter()= mutableListOf<Category>(
                Category("001",
                        "24 수요일"),
                Category("002",
                        "24 수요일"),
                Category("003",
                        "24 수요일"),
                Category("004",
                        "24 수요일"),
                Category("005",
                        "24 수요일"),
                Category("006",
                        "24 수요일"),
                Category("007",
                        "24 수요일")

        )

        fun mocksChapterMovie()= mutableListOf<Category>(
                Category("001",
                        "제1회"),
                Category("002",
                        "제2회"),
                Category("003",
                        "제3회"),
                Category("004",
                        "제4회"),
                Category("005",
                        "제5회"),
                Category("006",
                        "제6회"),
                Category("007",
                        "제7회"),
                Category("008",
                        "제8회"),
                Category("009",
                        "제9회"),
                Category("010",
                        "제10회"),
                Category("011",
                        "제11회"),
                Category("012",
                        "제12회"),
                Category("013",
                        "제13회"),
                Category("014",
                        "제14회")

        )
    }
}