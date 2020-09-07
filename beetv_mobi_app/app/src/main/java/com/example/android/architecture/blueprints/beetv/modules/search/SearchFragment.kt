package com.example.android.architecture.blueprints.beetv.modules.search

import android.animation.Animator
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.architecture.blueprints.beetv.R
import com.example.android.architecture.blueprints.beetv.data.adapter.TopMovieAdapter
import com.example.android.architecture.blueprints.beetv.common.basegui.BaseFragment
import com.example.android.architecture.blueprints.beetv.data.models.Movie
import com.example.android.architecture.blueprints.beetv.databinding.FragmentSearchBinding
import com.example.android.architecture.blueprints.beetv.util.getViewModelFactory
import com.example.android.architecture.blueprints.beetv.util.hide
import com.example.android.architecture.blueprints.beetv.util.show
import com.example.android.architecture.blueprints.beetv.widgets.AutoLayoutManager
import com.example.android.architecture.blueprints.beetv.widgets.KeyboardItemView
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroItemFrameLayout
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderHandler
import com.example.android.architecture.blueprints.beetv.widgets.metro.MetroViewBorderImpl


class SearchFragment : BaseFragment(), View.OnClickListener {
    private val viewModel by viewModels<SearchViewModel> { getViewModelFactory() }
    private lateinit var viewDataBinding: FragmentSearchBinding

    private lateinit var metroViewBorderImpl: MetroViewBorderImpl
    private lateinit var metroViewBorderImpl2: MetroViewBorderImpl
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewDataBinding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            click = ClickProxy(viewModel, this, context!!)
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val roundedFrameLayout = FrameLayout(context)
        roundedFrameLayout.clipChildren = false
        metroViewBorderImpl = MetroViewBorderImpl(roundedFrameLayout)
        metroViewBorderImpl2 = MetroViewBorderImpl(roundedFrameLayout)
        metroViewBorderImpl2.setBackgroundResource(R.drawable.border_color_red)
        metroViewBorderImpl.attachTo(viewDataBinding.main)
        metroViewBorderImpl2.attachTo(viewDataBinding.rvResult)
        metroViewBorderImpl.getViewBorder().addOnFocusChanged(object : MetroViewBorderHandler.FocusListener {
            override fun onFocusChanged(oldFocus: View?, newFocus: View?) {
                metroViewBorderImpl.getView().setTag(newFocus)
                changeBackgroundButton(oldFocus, newFocus)
            }
        })


        metroViewBorderImpl.getViewBorder().addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                val t: View? = metroViewBorderImpl.getView().findViewWithTag("top")
                if (t != null) {
                    (t.parent as ViewGroup).removeView(t)
                    (metroViewBorderImpl.getView().getTag(metroViewBorderImpl.getView().getId()) as ViewGroup).addView(t)
                }
            }


            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator?) {
                val nf = metroViewBorderImpl.getView().getTag() as? View
                if (nf != null) {
                    val top = nf.findViewWithTag<View>("top")
                    if (top != null) {
                        (top.parent as ViewGroup).removeView(top)
                        (metroViewBorderImpl.getView() as ViewGroup).addView(top)
                        metroViewBorderImpl.getView().setTag(metroViewBorderImpl.getView().getId(), nf)
                    }
                }
            }
        })
        getListMovie()
        initButton()
    }
    private fun getListMovie() {
        val gridlayoutManager: GridLayoutManager = AutoLayoutManager(context, 4)
        gridlayoutManager.setOrientation(GridLayoutManager.VERTICAL)
        viewDataBinding.rvResult.setLayoutManager(gridlayoutManager)
        metroViewBorderImpl.attachTo(viewDataBinding.rvResult)
        val widthItem = context!!.resources.getDimensionPixelOffset(R.dimen.size_130)
        val heightItem = widthItem*384/265
        val movieAdapter = TopMovieAdapter(Movie.mocks(), context!!, widthItem, heightItem)
//
//        movieAdapter.mOnItemClickListener={
//            viewModel.openMovieDetail(it.id)
//        }
        viewDataBinding.rvResult.adapter = movieAdapter

    }
    private fun changeBackgroundButton(oldView: View?, newView: View?) {

        if(oldView != null){
            if (oldView is KeyboardItemView) {
                oldView.setColor(R.color.black)
            }
            if (oldView is MetroItemFrameLayout){
                oldView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.black))
                if (oldView.getChildAt(0) is TextView){
                    (oldView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!,R.color.white))
                }
            }
        }

        if(newView != null){
            if (newView is KeyboardItemView) {
                newView.setColor(R.color.white)
            }
            if (newView is MetroItemFrameLayout){
                newView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.white))
                if (newView.getChildAt(0) is TextView){
                    (newView.getChildAt(0) as TextView).setTextColor(ContextCompat.getColor(context!!, R.color.black))
                }
            }

        }

    }

    private fun initButton(){
        viewDataBinding.btA.setOnClickListener (this)
        viewDataBinding.btB.setOnClickListener (this)
        viewDataBinding.btC.setOnClickListener (this)
        viewDataBinding.btD.setOnClickListener (this)
        viewDataBinding.btE.setOnClickListener (this)
        viewDataBinding.btF.setOnClickListener (this)
        viewDataBinding.btG.setOnClickListener (this)
        viewDataBinding.btH.setOnClickListener (this)
        viewDataBinding.btI.setOnClickListener (this)
        viewDataBinding.btJ.setOnClickListener (this)
        viewDataBinding.btK.setOnClickListener (this)
        viewDataBinding.btL.setOnClickListener (this)
        viewDataBinding.btM.setOnClickListener (this)
        viewDataBinding.btN.setOnClickListener (this)
        viewDataBinding.btO.setOnClickListener (this)
        viewDataBinding.btP.setOnClickListener (this)
        viewDataBinding.btQ.setOnClickListener (this)
        viewDataBinding.btR.setOnClickListener (this)
        viewDataBinding.btS.setOnClickListener (this)
        viewDataBinding.btT.setOnClickListener (this)
        viewDataBinding.btU.setOnClickListener (this)
        viewDataBinding.btW.setOnClickListener (this)
        viewDataBinding.btX.setOnClickListener (this)
        viewDataBinding.btY.setOnClickListener (this)
        viewDataBinding.btZ.setOnClickListener (this)
        viewDataBinding.btV.setOnClickListener (this)
        viewDataBinding.btOne.setOnClickListener (this)
        viewDataBinding.btTwo.setOnClickListener (this)
        viewDataBinding.btThree.setOnClickListener (this)
        viewDataBinding.btFour.setOnClickListener (this)
        viewDataBinding.btFive.setOnClickListener (this)
        viewDataBinding.btSix.setOnClickListener (this)
        viewDataBinding.btSeven.setOnClickListener (this)
        viewDataBinding.btEight.setOnClickListener (this)
        viewDataBinding.btNine.setOnClickListener (this)
        viewDataBinding.btZero.setOnClickListener (this)
    }

    public class ClickProxy(val viewModel: SearchViewModel, val viewBinding : FragmentSearchBinding, val context : Context) {


        fun emptySearch(){
            viewBinding.etSearch.text.clear()
        }

        fun deleteSearch(){
            val length: Int = viewBinding.etSearch.text.length
            if (length > 0) {
                viewBinding.etSearch.text.delete(length - 1, length);
            }
        }
        fun changeEnglish(){
            viewBinding.btA.setCharacter(context.getString(R.string.a_en))
            viewBinding.btB.setCharacter(context.getString(R.string.b_en))
            viewBinding.btC.setCharacter(context.getString(R.string.c_en))
            viewBinding.btD.setCharacter(context.getString(R.string.d_en))
            viewBinding.btE.setCharacter(context.getString(R.string.e_en))
            viewBinding.btF.setCharacter(context.getString(R.string.f_en))
            viewBinding.btG.setCharacter(context.getString(R.string.g_en))
            viewBinding.btH.setCharacter(context.getString(R.string.h_en))
            viewBinding.btI.setCharacter(context.getString(R.string.i_en))
            viewBinding.btJ.setCharacter(context.getString(R.string.j_en))
            viewBinding.btK.setCharacter(context.getString(R.string.k_en))
            viewBinding.btL.setCharacter(context.getString(R.string.l_en))
            viewBinding.btM.setCharacter(context.getString(R.string.m_en))
            viewBinding.btN.setCharacter(context.getString(R.string.n_en))
            viewBinding.btO.setCharacter(context.getString(R.string.o_en))
            viewBinding.btP.setCharacter(context.getString(R.string.p_en))
            viewBinding.btQ.setCharacter(context.getString(R.string.q_en))
            viewBinding.btR.setCharacter(context.getString(R.string.r_en))
            viewBinding.btS.setCharacter(context.getString(R.string.s_en))
            viewBinding.btT.setCharacter(context.getString(R.string.t_en))
            viewBinding.btU.setCharacter(context.getString(R.string.u_en))
            viewBinding.btV.setCharacter(context.getString(R.string.v_en))
            viewBinding.btW.setCharacter(context.getString(R.string.w_en))
            viewBinding.btX.setCharacter(context.getString(R.string.x_en))
            viewBinding.btY.setCharacter(context.getString(R.string.y_en))
            viewBinding.btZ.setCharacter(context.getString(R.string.z_en))
            viewBinding.btOne.setCharacter(context.getString(R.string.one))
            viewBinding.btTwo.setCharacter(context.getString(R.string.two))
            viewBinding.btThree.setCharacter(context.getString(R.string.three))
            viewBinding.btFour.show()
            viewBinding.btFive.show()
            viewBinding.btSix.show()
            viewBinding.btSeven.show()
            viewBinding.btEight.show()
            viewBinding.btNine.show()
            viewBinding.btZero.show()
            val layoutParams =  viewBinding.btKorean.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.BELOW,R.id.bt_five)
            val layoutParams2 =  viewBinding.btEnglish.layoutParams as RelativeLayout.LayoutParams
            layoutParams2.addRule(RelativeLayout.BELOW,R.id.bt_five)
        }

        fun changeKorea(){
            viewBinding.btA.setCharacter(context.getString(R.string.a_ko))
            viewBinding.btB.setCharacter(context.getString(R.string.b_ko))
            viewBinding.btC.setCharacter(context.getString(R.string.c_ko))
            viewBinding.btD.setCharacter(context.getString(R.string.d_ko))
            viewBinding.btE.setCharacter(context.getString(R.string.e_ko))
            viewBinding.btF.setCharacter(context.getString(R.string.f_ko))
            viewBinding.btG.setCharacter(context.getString(R.string.g_ko))
            viewBinding.btH.setCharacter(context.getString(R.string.h_ko))
            viewBinding.btI.setCharacter(context.getString(R.string.i_ko))
            viewBinding.btJ.setCharacter(context.getString(R.string.j_ko))
            viewBinding.btK.setCharacter(context.getString(R.string.k_ko))
            viewBinding.btL.setCharacter(context.getString(R.string.l_ko))
            viewBinding.btM.setCharacter(context.getString(R.string.m_ko))
            viewBinding.btN.setCharacter(context.getString(R.string.n_ko))
            viewBinding.btO.setCharacter(context.getString(R.string.o_ko))
            viewBinding.btP.setCharacter(context.getString(R.string.p_ko))
            viewBinding.btQ.setCharacter(context.getString(R.string.q_ko))
            viewBinding.btR.setCharacter(context.getString(R.string.r_ko))
            viewBinding.btS.setCharacter(context.getString(R.string.s_ko))
            viewBinding.btT.setCharacter(context.getString(R.string.t_ko))
            viewBinding.btU.setCharacter(context.getString(R.string.u_ko))
            viewBinding.btV.setCharacter(context.getString(R.string.v_ko))
            viewBinding.btW.setCharacter(context.getString(R.string.w_ko))
            viewBinding.btX.setCharacter(context.getString(R.string.x_ko))
            viewBinding.btY.setCharacter(context.getString(R.string.y_ko))
            viewBinding.btZ.setCharacter(context.getString(R.string.z_ko))

            viewBinding.btOne.setCharacter(context.getString(R.string.one_ko))
            viewBinding.btTwo.setCharacter(context.getString(R.string.two_ko))
            viewBinding.btThree.setCharacter(context.getString(R.string.three_ko))
            viewBinding.btFour.hide()
            viewBinding.btFive.hide()
            viewBinding.btSix.hide()
            viewBinding.btSeven.hide()
            viewBinding.btEight.hide()
            viewBinding.btNine.hide()
            viewBinding.btZero.hide()

            val layoutParams =  viewBinding.btKorean.layoutParams as RelativeLayout.LayoutParams
            layoutParams.addRule(RelativeLayout.BELOW,R.id.bt_y)
            val layoutParams2 =  viewBinding.btEnglish.layoutParams as RelativeLayout.LayoutParams
            layoutParams2.addRule(RelativeLayout.BELOW,R.id.bt_y)
        }
    }

    override fun onClick(v: View?) {

        if(v is KeyboardItemView){
            viewDataBinding.etSearch.setText(viewDataBinding.etSearch.text.toString().plus(v.getCharacter()))
        }
    }
}