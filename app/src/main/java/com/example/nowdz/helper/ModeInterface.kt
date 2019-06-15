package com.example.nowdz.helper

import android.content.Context
import android.preference.PreferenceManager
import com.example.nowdz.Constante.DARK_THEME
import com.example.nowdz.Constante.KEY_CURRENT_THEME
import com.example.nowdz.Constante.LIGHT_THEME

interface ModeInterface {

    fun getCurrentTheme(context: Context,theme: String,key: String) : String?{
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        return  sharedPref.getString(
            key,
            theme
        )
    }

    fun changeTheme(theme:String,key : String,context: Context){
        val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPref.edit().putString(
            key,
            theme
        ).apply()
    }


}