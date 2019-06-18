package com.example.nowdz.ui.activities

import android.content.Context
import android.os.Bundle
import android.widget.ImageView
import com.example.nowdz.*
import com.example.nowdz.helper.LocaleHelper
import com.example.nowdz.helper.ModeInterface
import com.example.nowdz.ui.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*



class SettingsActivity : BaseActivity(),ModeInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        themechange()
        langueChange()

        val backarrow = findViewById<ImageView>(R.id.back_toolbar)
        backarrow!!.setOnClickListener{
            if ( fragmentManager.backStackEntryCount > 0)
            {
                fragmentManager.popBackStack()
            }
            super.onBackPressed()

        }
    }

    private fun themechange(){
        val currentTheme = getCurrentTheme(this@SettingsActivity,
            LIGHT_THEME,
            KEY_CURRENT_THEME
        )
        mintTheme.isChecked = currentTheme == DARK_THEME
        mintTheme.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked)
                changeTheme(DARK_THEME, KEY_CURRENT_THEME,this@SettingsActivity)
            else
                changeTheme(LIGHT_THEME, KEY_CURRENT_THEME,this@SettingsActivity)
            recreate()
        }
    }

    private fun langueChange(){
        val currentLangue = getCurrentTheme(this@SettingsActivity,
            FRENCH,
            KEY_CURRENT_LANGUE
        )
        language.isChecked = currentLangue == ARABIC
        language.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked)
                changeTheme(ARABIC, KEY_CURRENT_LANGUE,this@SettingsActivity)
            else
                changeTheme(FRENCH, KEY_CURRENT_LANGUE,this@SettingsActivity)
            recreate()
        }
    }
}
