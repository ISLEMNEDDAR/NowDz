package com.example.nowdz.ui

import android.os.Bundle
import android.widget.ImageView
import com.example.nowdz.DARK_THEME
import com.example.nowdz.KEY_CURRENT_THEME
import com.example.nowdz.LIGHT_THEME
import com.example.nowdz.R
import com.example.nowdz.helper.ModeInterface
import com.example.nowdz.ui.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*



class SettingsActivity : BaseActivity(),ModeInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
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
        var backarrow = findViewById<ImageView>(R.id.back_toolbar)
        backarrow!!.setOnClickListener{
            if ( getFragmentManager().backStackEntryCount > 0)
            {
                getFragmentManager().popBackStack()
            }
            super.onBackPressed()

        }
    }

}
