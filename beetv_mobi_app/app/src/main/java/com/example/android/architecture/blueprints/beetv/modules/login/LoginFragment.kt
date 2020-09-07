package com.example.android.architecture.blueprints.beetv.modules.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.common.basegui.BaseFragment
import com.example.android.architecture.blueprints.beetv.databinding.FragmentLoginBinding
import com.example.android.architecture.blueprints.beetv.util.Constants
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl

class LoginFragment : BaseFragment() {
    private val viewModel by viewModels<LoginViewModel> { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentLoginBinding
    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentLoginBinding.inflate(inflater, container, false).apply {
            click = ClickProxy(viewModel, this@LoginFragment)
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false
        metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        metroViewBorderImpl.attachTo(viewDataBinding.main)
        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                metroViewBorderImpl.getView().setTag(newFocus)
                changeBackgroundButton(oldFocus, newFocus)
            }
        })

    }

    private fun changeBackgroundButton(oldView: View?, newView: View?) {

        if(oldView != null){

            if (oldView is MetroItemFrameLayout){
                oldView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.black))
                if (oldView.getChildAt(0) is TextView){
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!,R.color.white))
                }
            }
        }

        if(newView != null){

            if (newView is MetroItemFrameLayout){
                newView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.white))
                if (newView.getChildAt(0) is TextView){
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.black))
                }
            }

        }

    }
    public class ClickProxy(val viewModel: LoginViewModel, val fragment: LoginFragment) {

        fun login(){
            val action = LoginFragmentDirections.actionLoginFragmentDestToHomeFragmentDest(Constants.LOGIN)
            fragment.findNavController().navigate(action)
        }

        fun register(){
            val action = LoginFragmentDirections.actionLoginFragmentDestToRegisterFragmentDest()
            fragment.findNavController().navigate(action)
        }
    }
}