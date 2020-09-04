/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.architecture.blueprints.todoapp

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.example.android.architecture.blueprints.todoapp.addedittask.AddEditTaskViewModel
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.favorite.FavoriteViewModel
import com.example.android.architecture.blueprints.todoapp.home.HomeViewModel
import com.example.android.architecture.blueprints.todoapp.login.LoginViewModel
import com.example.android.architecture.blueprints.todoapp.menu.MenuViewModel
import com.example.android.architecture.blueprints.todoapp.movie_detail.MovieDetailViewModel
import com.example.android.architecture.blueprints.todoapp.register.RegisterViewModel
import com.example.android.architecture.blueprints.todoapp.search.SearchViewModel
import com.example.android.architecture.blueprints.todoapp.splash.SplashViewModel
import com.example.android.architecture.blueprints.todoapp.player.PlayerViewModel
import com.example.android.architecture.blueprints.todoapp.statistics.StatisticsViewModel
import com.example.android.architecture.blueprints.todoapp.taskdetail.TaskDetailViewModel
import com.example.android.architecture.blueprints.todoapp.tasks.TasksViewModel

/**
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
        private val tasksRepository: TasksRepository,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
    ) = with(modelClass) {
        when {
            isAssignableFrom(StatisticsViewModel::class.java) -> StatisticsViewModel(tasksRepository)
            isAssignableFrom(TaskDetailViewModel::class.java) -> TaskDetailViewModel(tasksRepository)
            isAssignableFrom(AddEditTaskViewModel::class.java) -> AddEditTaskViewModel(tasksRepository)
            isAssignableFrom(TasksViewModel::class.java) -> TasksViewModel(tasksRepository, handle)
            isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(tasksRepository, handle)
            isAssignableFrom(MenuViewModel::class.java) -> MenuViewModel(tasksRepository, handle)
            isAssignableFrom(MovieDetailViewModel::class.java) -> MovieDetailViewModel(tasksRepository, handle)
            isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel()
            isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel()
            isAssignableFrom(FavoriteViewModel::class.java) -> FavoriteViewModel()
            isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel()
            isAssignableFrom(RegisterViewModel::class.java) -> RegisterViewModel()
            isAssignableFrom(PlayerViewModel::class.java) -> PlayerViewModel(tasksRepository, handle)

            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
