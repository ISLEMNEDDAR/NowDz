package com.example.nowdz


import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.support.v7.widget.PopupMenu
import android.util.Log
import android.widget.Toast
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.example.nowdz.Fragment.*
import android.widget.ImageView
import com.example.nowdz.Fragment.AcuilleFragment
import com.example.nowdz.Fragment.FavorisFragment
import com.example.nowdz.Fragment.TitreFragement
import com.islem.rvhlibrary.RecycleViewHelper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.acuille_content.*
import kotlinx.android.synthetic.main.card_news_secondaire.*



class MainActivity : BaseActivity(),NavigationView.OnNavigationItemSelectedListener  {
    private var settingMenu : ImageView?=null


    private var topToolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val setting = findViewById<ImageView>(R.id.extra_menu)

        settingMenu = findViewById(R.id.extra_menu)

        chargerFagment(AcuilleFragment())
        setToolbar()
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, topToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.colorWhiteless)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)
        navigation()
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
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_tools -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

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
     *  Le menu popup
     */

    private fun popsurf () {
        card2_menu.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_popup_save -> {
                        Toast.makeText(this, "Showing Save Toast!", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.menu_popup_share -> {
                        Toast.makeText(this, "Showing Share Toast!", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.menu_popup_access -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://resocoder.com"))
                        startActivity(intent)
                        true
                    }
                    R.id.menu_popup_hide -> {
                        Toast.makeText(this, "Showing Hide Toast!", Toast.LENGTH_LONG).show()
                        true
                    }
                    R.id.nav_home -> {
                        // Handle the camera action
                        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    R.id.nav_gallery -> {
                        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    R.id.nav_slideshow -> {
                        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    R.id.nav_tools -> {
                        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    R.id.nav_share -> {
                        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    R.id.nav_send -> {
                        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                        drawerLayout.closeDrawer(GravityCompat.START)
                        true
                    }
                    else -> false
                }



            }
        popupMenu.inflate(R.menu.menu_popup)
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass
                .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(mPopup, true)
        } catch (e: Exception){
            Log.e("Main", "Error showing menu icons.", e)
        } finally {
            popupMenu.show()
        }
        }
    }

    /**
     *
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_toolbar_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    /**
     *
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item!!.itemId){

        }

        return super.onOptionsItemSelected(item)
    }
}
