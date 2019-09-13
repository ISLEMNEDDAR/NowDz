package com.example.nowdz.helper

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.example.nowdz.ui.activities.BaseActivity

interface onWebView  {
    fun showActivity(activity : BaseActivity, cls : Class<*>){
        val intent = Intent (activity.applicationContext, cls)
        activity.startActivity(intent)
    }
    fun showActivity(activity : FragmentActivity, cls : Class<*>){
        val intent = Intent (activity.applicationContext, cls)
        activity.startActivity(intent)
    }
}