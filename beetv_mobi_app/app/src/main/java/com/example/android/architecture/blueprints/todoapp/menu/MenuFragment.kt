package com.example.android.architecture.blueprints.todoapp.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.android.architecture.blueprints.todoapp.base.BaseFragment
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentHomeBinding
import com.example.android.architecture.blueprints.todoapp.databinding.FragmentMenuBinding
import com.example.android.architecture.blueprints.todoapp.home.HomeViewModel
import com.example.android.architecture.blueprints.todoapp.util.getViewModelFactory

class MenuFragment : BaseFragment() {
    private val viewModel by viewModels<MenuViewModel> { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentMenuBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMenuBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
    }
}