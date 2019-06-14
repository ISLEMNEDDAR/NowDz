package com.example.nowdz

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import com.example.nowdz.Constante.DARK_THEME
import com.example.nowdz.Constante.KEY_CURRENT_THEME
import com.example.nowdz.Constante.LIGHT_THEME
import com.example.nowdz.helper.ModeInterface

open class BaseActivity : AppCompatActivity(),ModeInterface {

    protected var currentTheme: String? =null
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentTheme = getCurrentTheme(this@BaseActivity, LIGHT_THEME, KEY_CURRENT_THEME)
        setAppTheme(currentTheme!!)
    }

    override fun onResume() {
        super.onResume()
        val selectedTheme = getCurrentTheme(this@BaseActivity, LIGHT_THEME, KEY_CURRENT_THEME)
        if(currentTheme != selectedTheme)
            recreate()
    }

    private fun setAppTheme(currentTheme: String) {
        when (currentTheme) {
            DARK_THEME -> setTheme(R.style.AppDarkTheme)
            else -> setTheme(R.style.AppTheme)
        }
    }
}