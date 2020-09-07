package com.example.android.architecture.blueprints.beetv.modules.player

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.android.architecture.blueprints.beetv.data.source.TasksRepository

class PlayerViewModel(
        private val tasksRepository: TasksRepository,
        private val savedStateHandle: SavedStateHandle
) : ViewModel() {
}