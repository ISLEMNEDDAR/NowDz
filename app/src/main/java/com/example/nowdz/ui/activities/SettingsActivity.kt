package com.example.nowdz.ui.activities

import androidx.appcompat.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.example.nowdz.*
import com.example.nowdz.controller.CategorieController
import com.example.nowdz.helper.ModeInterface
import com.example.nowdz.helper.SharedPreferencesHelper
import com.example.nowdz.model.Categories
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_setting.*


class SettingsActivity : BaseActivity(),ModeInterface {
    private var pref: SharedPreferencesHelper? = null
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private var deconnexionButton : Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        /**
         *
         */
        deconnexionButton = findViewById(R.id.deconnexionButton)
        deconnexionButton!!.setOnClickListener {
            googleDeconnexion()
        }

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

    /**
     * on start activity
     */
    override fun onStart() {
        super.onStart()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(SharedPreferencesHelper(this@SettingsActivity, NOM_FICHER_LOGIN).avoirIdUserS())
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }
    /**
     * Pour Deconnecter
     */
    private fun deconnecter(){
        val login = Intent(this@SettingsActivity, LoginActivity::class.java)
        startActivity(login)
        this@SettingsActivity.finish()
    }
    /**
     * deconnection Google
     */
    private fun googleDeconnexion(){
        //signIn out from the the google account
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) {
                pref = SharedPreferencesHelper(this@SettingsActivity, NOM_FICHER_LOGIN)
                pref!!.sharedPreferences.edit().clear().apply()
                deconnecter()
            }

        // revokeAccess
        mGoogleSignInClient!!.revokeAccess()
            .addOnCompleteListener(this) {
                // ...
            }
    }
}
