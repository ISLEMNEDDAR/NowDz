package com.example.nowdz.ui.activities


import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.Toolbar
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.util.Log
import com.google.android.material.navigation.NavigationView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nowdz.Google_Token
import com.example.nowdz.MAX_SMS_MESSAGE_LENGTH
import com.example.nowdz.ui.activities.Fragment.AcuilleFragment
import com.example.nowdz.ui.activities.Fragment.FavorisFragment
import com.example.nowdz.ui.activities.Fragment.TitreFragement
import com.example.nowdz.R
import com.example.nowdz.Service.ArticleService
import com.example.nowdz.Service.ServiceBuilder
import com.example.nowdz.SEND_SMS_PERMISSION_REQUEST_CODE
import com.example.nowdz.helper.BeamsNotif
import com.example.nowdz.helper.SharedPreferenceInterface
import com.example.nowdz.helper.SharedPreferencesHelper
import com.example.nowdz.model.Article
import com.example.nowdz.model.RequestFavoris
import com.example.nowdz.ui.activities.Fragment.PreferenceFragment
import com.example.nowdz.viewModel.ArticleViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.pusher.pushnotifications.BeamsCallback
import com.pusher.pushnotifications.PushNotifications
import com.pusher.pushnotifications.PusherCallbackError
import kotlinx.android.synthetic.main.acuille_content.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener,SharedPreferenceInterface {
    private var settingMenu : ImageView?=null
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private var pref: SharedPreferencesHelper? = null
    private var topToolbar: Toolbar? = null
    private lateinit var articleViewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        articleViewModel = ViewModelProviders.of(this@MainActivity).get(
            ArticleViewModel::class.java
        )
        /**
         * Initialisation
         */
        val setting = findViewById<ImageView>(R.id.extra_menu)
        settingMenu = findViewById(R.id.extra_menu)

        /**
         * Methode
         */
        PushNotifications.start(applicationContext,"2d3f5328-bba4-4d85-8320-a33ede693d08")

        //PushNotifications.addDeviceInterest("hello")
        chargerFagment(AcuilleFragment())
        setToolbar()
        initDrawerMenu()
        navigation()
        //getAllFavoris()
        PushNotifications.setUserId(
            avoirIdUser(applicationContext).toString(),
            BeamsNotif.tokenProvider(avoirIdUser(applicationContext).toString()),
            object : BeamsCallback<Void,PusherCallbackError>{
                override fun onSuccess(vararg values: Void) {
                    Log.i("BeamsAuth", "Beams login success");
                }

                override fun onFailure(error: PusherCallbackError) {
                    Log.e("BeamsAuth", "Could not login to Beams: ${error.message}");
                }
            }
        )
    }

    /***
     *
     * ***/
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val contactData = data!!.data
            val c = contentResolver.query(contactData!!, null, null, null, null)
            if (c!!.moveToFirst()) {

                var phoneNumber = ""
                var emailAddress = ""
                val name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID))
                //http://stackoverflow.com/questions/866769/how-to-call-android-contacts-list   our upvoted answer

                var hasPhone = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))

                if (hasPhone.equals("1", ignoreCase = true))
                    hasPhone = "true"
                else
                    hasPhone = "false"

                if (java.lang.Boolean.parseBoolean(hasPhone)) {
                    val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null)
                    while (phones!!.moveToNext()) {
                        phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    }
                    phones.close()
                }

                // Find Email Addresses
                val emails = contentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId, null, null)
                while (emails!!.moveToNext()) {
                    emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                }
                emails.close()

                //mainActivity.onBackPressed();
                // Toast.makeText(mainactivity, "go go go", Toast.LENGTH_SHORT).show();


                //   sendSMS(phoneNumber,"Hi from android")
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS)
                    != PackageManager.PERMISSION_GRANTED) {
                    // Permission is not granted
                    ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.SEND_SMS), SEND_SMS_PERMISSION_REQUEST_CODE)

                    Log.i("msg","pas de permissinon")

                }
                else {sendSMS(phoneNumber,"Hi from android")}

                // val smsManager = SmsManager.getDefault()
                // smsManager.sendTextMessage(phoneNumber, null,"Hhhhh", null, null)

                // Log.d("curs", "$name num$phoneNumber mail$emailAddress")
            }
            c.close()
        }
    }

    /*****
     *
     *
     *
     *
     */

    // ---sends an SMS message to another device---
    fun sendSMS(phoneNumber: String, message: String) {

        val smsManager = SmsManager.getDefault()

        val length = message.length
        if (length > MAX_SMS_MESSAGE_LENGTH) {
            val messagelist = smsManager.divideMessage(message)
            smsManager.sendMultipartTextMessage(phoneNumber, null, messagelist, null, null)
        } else
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
    }

    /**
     *
     */
    private fun initDrawerMenu(){
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, topToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.colorWhiteless)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
    }
    /**
     *
     */
    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    /**
     * Preparer le toolbar
     */
    private fun setToolbar() {
        topToolbar = findViewById(R.id.toolbar_accuille)
        setSupportActionBar(topToolbar)
    }

    /**
     * la navigation par le navigation bottom entre les fragement
     */
    private fun navigation() {
        navigation_bar.setOnNavigationItemSelectedListener {
            var fragment: Fragment? = null
            when (it.itemId) {
                R.id.item_accuille -> {
                    fragment = AcuilleFragment()
             }
                R.id.item_favoris -> {

                    fragment = FavorisFragment()

                }
                R.id.item_titre -> {
                    fragment = TitreFragement()
                }
                R.id.item_preference ->{
                    fragment =PreferenceFragment()
                }
            }
            return@setOnNavigationItemSelectedListener chargerFagment(fragment)
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                // Handle the camera action
            }

            R.id.nav_share -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
    /**
     * le changement de fragement
     */
    @SuppressLint("CommitTransaction")
    private fun chargerFagment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_id, fragment).commit()
            return true
        }
        return false
    }
    /**
     * Preparer l'historique

    private fun setSavedActivity() {
        val intent = Intent (this, Main2Activity::class.java)
        startActivity(intent)
    }
     */


    /**
     *
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar_main,menu)


        /**
         *
         */
        val mSearch = menu!!.findItem(R.id.search_menu)

        val mSearchView = mSearch.actionView as SearchView
        mSearchView.queryHint = "Search"

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // filter
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    /**
     *
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){
            R.id.extra_menu ->{
                val settingIntent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(settingIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    /**
     * Google
     */

    /**
     * on start activity
     */
    override fun onStart() {
        super.onStart()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Google_Token)
            .requestEmail()
            .build()

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

    }
    /**
     * deconnection Google
     */
    private fun googleDeconnexion(){
        //signIn out from the the google account
        mGoogleSignInClient!!.signOut()
            .addOnCompleteListener(this) {
                pref = sharedPref(this@MainActivity,"google")
                pref!!.sharedPreferences.edit().clear().apply()
                deconnecter()
            }

        // revokeAccess
        mGoogleSignInClient!!.revokeAccess()
            .addOnCompleteListener(this) {
                // ...
            }
    }

    /**
     * Pour Deconnecter
     */
    private fun deconnecter(){
        val login = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(login)
        this@MainActivity.finish()
    }


    private fun getAllFavoris(){
        var articleService = ServiceBuilder.buildService(ArticleService::class.java)
        var request = articleService.getAllFavoris(avoirIdUser(this@MainActivity).toString())
        request.enqueue(object : Callback<ArrayList<Article>> {
            override fun onResponse(call: Call<ArrayList<Article>>, response: Response<ArrayList<Article>>) {
                if(response.isSuccessful){
                    var listArticle = response.body()!!
                    listArticle.forEach {
                        article ->
                            article.suivi = true
                        articleViewModel.articleExist(article.titre!!,article.journal!!.name!!).observe(
                            this@MainActivity!!,
                            Observer {

                                if(it.size == 0){
                                    articleViewModel.insert(article)
                                }
                            }
                        )
                    }

                    }else{
                    getAllFavoris()
                }

            }
            override fun onFailure(call: Call<ArrayList<Article>>, t: Throwable) {
                getAllFavoris()
            }
        })

    }
}
