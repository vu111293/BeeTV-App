package com.example.android.architecture.blueprints.todoapp.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

class PlayerViewModel(
        private val tasksRepository: TasksRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}