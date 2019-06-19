package com.example.nowdz.ui.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageView
import com.example.nowdz.*
import com.example.nowdz.controller.CategorieController
import com.example.nowdz.helper.LocaleHelper
import com.example.nowdz.helper.ModeInterface
import com.example.nowdz.model.Categories
import com.example.nowdz.ui.activities.BaseActivity
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.card_view_article_enregistre.*


class SettingsActivity : BaseActivity(),ModeInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


        themechange()
        langueChange()
        thematiqueChange()
        val backarrow = findViewById<ImageView>(R.id.back_toolbar)
        backarrow!!.setOnClickListener{
            if ( fragmentManager.backStackEntryCount > 0)
            {
                fragmentManager.popBackStack()
            }
            super.onBackPressed()

        }
    }


    private fun thematiqueChange(){
        thematique.setOnClickListener {
            val dialog : Dialog
            val categories:ArrayList<Categories> = CategorieController.getAllCategorie().clone() as ArrayList<Categories>
            val titre : Array<String> = Array(categories.size){i->getString(categories[i].category)}
            val affiche : BooleanArray = BooleanArray(categories.size)

            for (i in 0 until categories.size){
                affiche[i] = categories[i].affichee
            }
            val itemsSelected = ArrayList<Int>()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Selectionner Thematique a voir : ")
            val negativeButton = builder.setMultiChoiceItems(
                titre, affiche
            ) { dialog, selectedItemId, isSelected ->
                affiche[selectedItemId] = isSelected
            }

                .setPositiveButton(getString(R.string.done)) { dialog, id ->
                    //Your logic when OK button is clicked
                    for (i in 0 until affiche.size) {
                        categories[i].affichee = affiche[i]
                    }
                    println("OK ${categories.toString()}")
                    CategorieController.removelisteCatg()
                    CategorieController.putAllCategorie(categories)
                    recreate()
                }
                .setNegativeButton(getString(R.string.cancel)) { dialog, id -> }
            dialog = builder.create()
            dialog.show()
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
