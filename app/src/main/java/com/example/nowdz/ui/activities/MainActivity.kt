package com.example.nowdz.ui.activities


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.example.nowdz.Google_Token
import com.example.nowdz.ui.activities.Fragment.AcuilleFragment
import com.example.nowdz.ui.activities.Fragment.FavorisFragment
import com.example.nowdz.ui.activities.Fragment.TitreFragement
import com.example.nowdz.R
import com.example.nowdz.helper.SharedPreferenceInterface
import com.example.nowdz.helper.SharedPreferencesHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.acuille_content.*


class MainActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener,SharedPreferenceInterface {
    private var settingMenu : ImageView?=null
    private var mGoogleSignInClient : GoogleSignInClient? = null
    private var pref: SharedPreferencesHelper? = null
    private var topToolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * Initialisation
         */
        val setting = findViewById<ImageView>(R.id.extra_menu)
        settingMenu = findViewById(R.id.extra_menu)

        /**
         * Methode
         */
        chargerFagment(AcuilleFragment())
        setToolbar()
        initDrawerMenu()
        navigation()
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
}
