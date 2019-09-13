package com.example.nowdz.ui.activities

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nowdz.*
import com.example.nowdz.helper.LocaleHelper
import com.example.nowdz.helper.ModeInterface

open class BaseActivity : AppCompatActivity(),ModeInterface {

    protected var currentTheme: String? =null
    protected var currentLangue : String? = null
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentTheme = getCurrentTheme(this@BaseActivity,
            LIGHT_THEME,
            KEY_CURRENT_THEME
        )

        currentLangue = getCurrentTheme(this@BaseActivity,
            FRENCH,
            KEY_CURRENT_LANGUE)

        setAppTheme(currentTheme!!)
        LocaleHelper.setLocale(this@BaseActivity,currentLangue)
    }

    override fun onResume() {
        super.onResume()
        val selectedTheme = getCurrentTheme(this@BaseActivity,
            LIGHT_THEME,
            KEY_CURRENT_THEME
        )
        val selectedLangue = getCurrentTheme(this@BaseActivity,
            FRENCH,
            KEY_CURRENT_LANGUE)
        if(currentTheme != selectedTheme || currentLangue != selectedLangue)
            recreate()
    }

    private fun setAppTheme(currentTheme: String) {
        when (currentTheme) {
            DARK_THEME -> setTheme(R.style.AppDarkTheme)
            else -> setTheme(R.style.AppTheme)
        }
    }
}