package com.example.android.architecture.blueprints.todoapp.util

class Constants {
    companion object{


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