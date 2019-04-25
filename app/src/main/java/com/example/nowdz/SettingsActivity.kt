package com.example.nowdz

import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_setting.*

const val KEY_CURRENT_THEME = "current_theme"
const val LIGHT_THEME = "light"
const val DARK_THEME = "dark"

class SettingsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(this)
        val currentTheme = sharedPref.getString(KEY_CURRENT_THEME, LIGHT_THEME)

        mintTheme.isChecked = currentTheme == DARK_THEME
        mintTheme.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked)
                sharedPref.edit().putString(KEY_CURRENT_THEME, DARK_THEME).apply()
            else
                sharedPref.edit().putString(KEY_CURRENT_THEME, LIGHT_THEME).apply()
            recreate()
        }
    }

}
