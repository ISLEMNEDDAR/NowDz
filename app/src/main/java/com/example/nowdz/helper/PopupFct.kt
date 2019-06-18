package com.example.nowdz.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.nowdz.ui.AffichageActivity
import com.example.nowdz.ui.ArticleEnregistreActivity
import com.example.nowdz.R
import com.example.nowdz.ui.ArticleActivity

class PopupFct(val context: Context,
               val view: View,
               val activity: Activity) : PopupMenu(context, view),GlobalHelper {
     fun onCLick() {
         setOnMenuItemClickListener {item ->
            when (item.itemId) {
                R.id.menu_popup_save -> {
                    switchActivity(context, AffichageActivity::class.java,activity)
                    true
                }
                R.id.menu_popup_share -> {
                    val shareintent = Intent(Intent.ACTION_SEND)
                    shareintent.type="type/palin"
                    val sharebody ="The body"
                    val sharesub= "The subject"
                    shareintent.putExtra(Intent.EXTRA_SUBJECT,sharebody)
                    shareintent.putExtra(Intent.EXTRA_TEXT,sharesub)
                    ContextCompat.startActivity(
                        context,
                        Intent.createChooser(shareintent, "Share article"),
                        Bundle()
                    )



                    Toast.makeText(context, "Showing Share Toast!", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menu_popup_access -> {
                    Toast.makeText(context, "Showing access Toast!", Toast.LENGTH_SHORT).show()


                        Log.i("webView","not null web")
                    val intent = Intent (view.context, ArticleActivity::class.java)
                    activity!!.startActivity(intent)
                    true

                    //var wv : WebView = view.findViewById(R.id.read_webview)
                }
                R.id.menu_popup_hide -> {
                    Toast.makeText(context, "Showing Hide Toast!", Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }

    }
      fun inflat(id : Int){
         this.inflate(R.menu.menu_popup)

         try {
             val fieldMPopup = android.support.v7.widget.PopupMenu::class.java.getDeclaredField("mPopup")
             fieldMPopup.isAccessible = true
             val mPopup = fieldMPopup.get(this)
             mPopup.javaClass
                 .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                 .invoke(mPopup, true)
         } catch (e: Exception){
             Log.e("Main", "Error showing menu icons.", e)
         } finally {
             this.show()
         }
     }
}