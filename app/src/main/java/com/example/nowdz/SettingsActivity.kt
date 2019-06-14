package com.example.nowdz

import android.os.Bundle
import android.preference.PreferenceManager
import com.example.nowdz.Constante.DARK_THEME
import com.example.nowdz.Constante.KEY_CURRENT_THEME
import com.example.nowdz.Constante.LIGHT_THEME
import com.example.nowdz.helper.ModeInterface
import kotlinx.android.synthetic.main.activity_setting.*



class SettingsActivity : BaseActivity(),ModeInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val currentTheme = getCurrentTheme(this@SettingsActivity, LIGHT_THEME, KEY_CURRENT_THEME)
        mintTheme.isChecked = currentTheme == DARK_THEME
        mintTheme.setOnCheckedChangeListener { view, isChecked ->
            if (isChecked)
                changeTheme(DARK_THEME, KEY_CURRENT_THEME,this@SettingsActivity)
            else
                changeTheme(LIGHT_THEME, KEY_CURRENT_THEME,this@SettingsActivity)
            recreate()
        }
    }

}
