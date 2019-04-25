package com.example.nowdz

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.nowdz.Fragment.AcuilleFragment
import com.example.nowdz.Fragment.FavorisFragment
import com.example.nowdz.Fragment.TitreFragement
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    private var topToolbar :Toolbar? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chargerFagment(AcuilleFragment())
        setToolbar()
        navigation()


    }

    /**
     * Preparer le toolbar
     */
    private fun setToolbar(){
        topToolbar = findViewById(R.id.toolbar_accuille)
        setSupportActionBar(topToolbar)
    }

    /**
     * la navigation par le navigation bottom entre les fragement
     */
    private fun navigation(){
        navigation_bar.setOnNavigationItemSelectedListener{
            var fragment : Fragment? = null
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

    /**
     * le changement de fragement
     */
    @SuppressLint("CommitTransaction")
    private fun chargerFagment(fragment: Fragment?):Boolean{
        if(fragment != null){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_id,fragment).commit()
            return true
        }
        return false
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.settings)
            startActivity(Intent(this, SettingsActivity::class.java))

        return super.onOptionsItemSelected(item)
    }

}
