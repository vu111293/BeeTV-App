package com.example.android.architecture.blueprints.beetv.modules.movie_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.architecture.blueprints.beetv.data.source.TasksRepository

class MovieDetailViewModel(
        private val tasksRepository: TasksRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // TODO: Implement the ViewModel
}