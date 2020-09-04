package com.example.android.architecture.blueprints.todoapp.data

data class Slide (
        val image :String
){
    companion object{
        fun mocks() = mutableListOf<Slide>(
                Slide("https://hdwallsource.com/img/2016/6/inception-movie-computer-wallpaper-49336-51002-hd-wallpapers.jpg"),
                Slide("https://www.danverslibrary.org/readthis/wp-content/uploads/2017/06/wonderwoman-1024x637.jpg"),
                Slide("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSkvs3ApgEKQ0K5sI1YikzUkJ8-AQS8-0kMMw&usqp=CAU"),
                Slide("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/94ab2ad3-e0d4-4764-85ad-873af742bd4a/ddakqly-48eba695-2dfd-4a5b-8cad-aa38453098c0.png/v1/fill/w_1192,h_670,strp/marvel_s_the_new_avengers_movie_banner_by_arkhamnatic_ddakqly-pre.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvOTRhYjJhZDMtZTBkNC00NzY0LTg1YWQtODczYWY3NDJiZDRhXC9kZGFrcWx5LTQ4ZWJhNjk1LTJkZmQtNGE1Yi04Y2FkLWFhMzg0NTMwOThjMC5wbmciLCJoZWlnaHQiOiI8PTEwODAiLCJ3aWR0aCI6Ijw9MTkyMCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS53YXRlcm1hcmsiXSwid21rIjp7InBhdGgiOiJcL3dtXC85NGFiMmFkMy1lMGQ0LTQ3NjQtODVhZC04NzNhZjc0MmJkNGFcL2Fya2hhbW5hdGljLTQucG5nIiwib3BhY2l0eSI6OTUsInByb3BvcnRpb25zIjowLjQ1LCJncmF2aXR5IjoiY2VudGVyIn19.y-rSNw-mZeBhW51kp_tH6zJzEHXzAUYSjBbr5kqWAz4")
        )
    }
}