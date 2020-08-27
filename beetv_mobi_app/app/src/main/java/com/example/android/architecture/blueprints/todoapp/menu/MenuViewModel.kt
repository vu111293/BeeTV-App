package com.example.android.architecture.blueprints.todoapp.menu

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository

class MenuViewModel(
        private val tasksRepository: TasksRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // TODO: Implement the ViewModel
}