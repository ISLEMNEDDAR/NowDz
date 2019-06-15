package com.example.nowdz.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.FragmentActivity

interface GlobalHelper{

    fun switchActivity(contextSource : Context,contextDist:Class<*>,activity :Activity){
        val intent = Intent(contextSource, contextDist)
        activity!!.startActivity(intent)
    }



}