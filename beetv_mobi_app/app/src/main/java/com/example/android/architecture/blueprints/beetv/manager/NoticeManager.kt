package com.example.android.architecture.blueprints.beetv.manager

import com.example.android.architecture.blueprints.beetv.data.models.BNotice
import com.example.android.architecture.blueprints.beetv.data.models.Notice

class NoticeManager {

    private val mNotices = mutableListOf<BNotice>()
    companion object {
        var mInstance: NoticeManager? = null
        fun getInstance(): NoticeManager {

            if (mInstance == null) {
                mInstance = NoticeManager()
            }
            return mInstance!!
        }
    }

    fun setData(notices : List<BNotice>?){
        mNotices.clear()
        if (notices != null)
            mNotices.addAll(notices)
    }

    fun getData() : MutableList<BNotice> = mNotices


}