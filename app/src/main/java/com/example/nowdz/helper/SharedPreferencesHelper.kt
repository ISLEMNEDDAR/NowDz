package com.example.nowdz.helper


import android.content.Context
import android.content.SharedPreferences
import com.example.nowdz.model.User
import java.math.BigInteger


class SharedPreferencesHelper(internal var context: Context,nom_fichier : String) {
    internal var sharedPreferences: SharedPreferences = context.getSharedPreferences(nom_fichier, Context.MODE_PRIVATE)

    /**
     * To set login details
     * @param userName : username to set
     * @param password : password to set
     */
    fun setLoginDetails(idUser : String,userNom: String) {
        val editor = sharedPreferences.edit()
        editor.putString("idUser",idUser)
        editor.putString("userNom", userNom)
        editor.apply()
    }

    fun getUser() : User{
        val pref = sharedPreferences
        val id = pref.getString("idUser",null)
        val nom = pref.getString("userName",null)
        return User(id,nom)
    }

    fun avoirIdUser() : BigInteger{
        val pref = sharedPreferences
        val id = pref.getString("idUser",null)
        return id!!.toBigInteger()
    }
    fun avoirIdUserS() : String{
        val pref = sharedPreferences
        val id = pref.getString("idUser",null)
        return id!!
    }

    companion object {
        private val TAG = "SharedPreferencesHelper"
    }

}