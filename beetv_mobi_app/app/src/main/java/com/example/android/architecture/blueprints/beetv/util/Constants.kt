package com.example.android.architecture.blueprints.beetv.util

class Constants {
    companion object{
        const val REGISTER = "REGISTER"
        const val LOGIN = "LOGIN"
    }

    enum class TYPE_CATEGORY {
        TV,
        MOVIE,
        DRAMA,
        ENTERTAINMENT,
        EDUCATION,
        CHILDRENTV,
        FAVORITE,
        PLAYBACK
    }

    enum class TYPE_MENU(val type  : Int){
        MENU(1),
        CHANNEL(2),
        PROGRAM(3),
        CHAPTER(4)

    }

}