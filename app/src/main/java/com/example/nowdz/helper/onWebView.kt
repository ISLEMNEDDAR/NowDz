package com.example.nowdz.helper

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.example.nowdz.BaseActivity
import com.example.nowdz.MainActivity

interface onWebView  {
    fun showActivity(activity : BaseActivity,cls : Class<*>){
        val intent = Intent (activity.applicationContext, cls)
        activity.startActivity(intent)
    }
    fun showActivity(activity : FragmentActivity,cls : Class<*>){
        val intent = Intent (activity.applicationContext, cls)
        activity.startActivity(intent)
    }
}