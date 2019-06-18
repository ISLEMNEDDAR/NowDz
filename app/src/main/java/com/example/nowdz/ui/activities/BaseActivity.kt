package com.example.nowdz.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.nowdz.DARK_THEME
import com.example.nowdz.KEY_CURRENT_THEME
import com.example.nowdz.LIGHT_THEME
import com.example.nowdz.R
import com.example.nowdz.helper.ModeInterface

open class BaseActivity : AppCompatActivity(),ModeInterface {

    protected var currentTheme: String? =null
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentTheme = getCurrentTheme(this@BaseActivity,
            LIGHT_THEME,
            KEY_CURRENT_THEME
        )
        setAppTheme(currentTheme!!)
    }

    override fun onResume() {
        super.onResume()
        val selectedTheme = getCurrentTheme(this@BaseActivity,
            LIGHT_THEME,
            KEY_CURRENT_THEME
        )
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