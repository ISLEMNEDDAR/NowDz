package com.example.nowdz.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.widget.Toast
import com.example.nowdz.Google_Token
import com.example.nowdz.NOM_FICHER_LOGIN
import com.example.nowdz.R
import com.example.nowdz.RC_SIGN_IN
import com.example.nowdz.Service.AuthService
import com.example.nowdz.Service.ServiceBuilder
import com.example.nowdz.helper.SharedPreferenceInterface
import com.example.nowdz.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(),SharedPreferenceInterface {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        googleAuth()
    }

    /**
     *
     */
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            super.onActivityResult(requestCode, resultCode, data)
            println("sign 123")
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    } //fin onActivityResult

    /**
     * sign in of google called from onActivity result
     */
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            // Signed in successfully, show authenticated UI.
            if (account != null) {
                connxionSuccefulGoogle(account)
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            connexionFailedGoogle(e)
        }

    } //fin handleSignInResult

    /**
     *
     */
    fun connxionSuccefulGoogle(account : GoogleSignInAccount){
        // la communication
        val token = account.idToken

        /**
         * avoir id prenom nom
         */
        saveInfoGoogle(account)
        val automobiliste = User(account.id!!,account.givenName!!)

        /**
         * la requete de retrofit
         */

        val authService = ServiceBuilder.buildService(AuthService::class.java)
        /*val requestCall = authService.setToken("${NOM_INIT_AUTH} ${LETTRE_GOOGLE_AUTH} ${token}",automobiliste)
        requestCall.enqueue(object : Callback<Automobiliste> {
            override fun onResponse(call: Call<Automobiliste>, response: Response<Automobiliste>) {
                if(response.isSuccessful){
                    dejaConnecte()
                }else{
                    Toast.makeText(this@LoginActivity,"Failed to connect", Toast.LENGTH_LONG)
                }

            }
            override fun onFailure(call: Call<Automobiliste>, t: Throwable) {
                Toast.makeText(this@LoginActivity,"Failed", Toast.LENGTH_LONG)
            }
        })*/





    } // fin connxionSuccefulGoogle

    /**
     *
     */
    fun connexionFailedGoogle(e : ApiException){
        Log.w("tag", "signInResult:failed code=" + e.printStackTrace())
        if(e.statusCode == 12500) {
            println(12500)
            Toast.makeText(this@LoginActivity,"Update your Google play Account", Toast.LENGTH_LONG)
        }

    }// fin connexionFailedGoogle

    /**
     *
     */
    private fun googleAuth(){
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Google_Token)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            println("account google ${account.toString()}")
            saveInfoGoogle(account)
            dejaConnecte()
        }
        sign_in_button.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }


    }

    private fun saveInfoGoogle(account: GoogleSignInAccount) {
        val pref = sharedPref(this@LoginActivity, NOM_FICHER_LOGIN)
        pref.setLoginDetails(account.id!!, account.givenName!!)
    }

    private fun dejaConnecte(){
        val accuille = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(accuille)
        this@LoginActivity.finish()
    }

}
