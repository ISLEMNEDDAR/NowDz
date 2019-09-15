package com.example.nowdz.helper

import android.content.Context
import com.example.nowdz.NOM_FICHER_LOGIN
import com.example.nowdz.model.User
import java.math.BigInteger

interface SharedPreferenceInterface {

    /**
     * retourne une instance de ShaeredPrefernceHelper en entrant le nom de Fichier
     */
    public fun sharedPref(context: Context, nomFichier : String) : SharedPreferencesHelper {
        return SharedPreferencesHelper(context,nomFichier)
    }

    /**
     * avoir le sinfo sous forme de Automobolisite
     */
    public fun avoirInfoUser(context : Context) : User {
        val pref = sharedPref(context, NOM_FICHER_LOGIN)
        return pref.getUser()
    }


    public fun avoirIdUser(context: Context) : BigInteger{
        val pref = sharedPref(context, NOM_FICHER_LOGIN)
        return pref.avoirIdUser()
    }
    public fun avoirSuivi(context: Context,nomFichier: String) : Boolean{
        val pref = sharedPref(context,nomFichier)
        return pref.getJournalSuivi(nomFichier)
    }

    public fun setSuivi(context: Context,nomFichier: String,suivi:Boolean){
        val sharedPreference = sharedPref(context,nomFichier)
        sharedPreference.setJournalSuivi(suivi,nomFichier)
    }
}