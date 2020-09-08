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
package com.example.android.architecture.blueprints.beetv.util

/**
 * Extension functions for Fragment.
 */

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import com.example.android.architecture.blueprints.beetv.BeeTVApplication
import com.example.android.architecture.blueprints.beetv.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as BeeTVApplication).taskRepository
    val movieRepository = (requireContext().applicationContext as BeeTVApplication).movieRepository
    return ViewModelFactory(repository, movieRepository,this)
}


fun ComponentActivity.getViewModelFactory(): ViewModelFactory {
    val repository = (applicationContext as BeeTVApplication).taskRepository
    val movieRepository = (applicationContext as BeeTVApplication).movieRepository
    return ViewModelFactory(repository, movieRepository, this)
}
