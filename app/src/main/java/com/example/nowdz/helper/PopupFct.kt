package com.example.nowdz.helper

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.nowdz.BaseActivity
import com.example.nowdz.Fragment.AcuilleFragment
import com.example.nowdz.Main2Activity
import com.example.nowdz.R

class PopupFct(val context: Context,
               val view: View,
               val activity: BaseActivity) : PopupMenu(context, view) {
    fun onCLick() {
         setOnMenuItemClickListener {item ->
            when (item.itemId) {
                R.id.menu_popup_save -> {
                    Toast.makeText(context, "Showing Save Toast!", Toast.LENGTH_LONG).show()
                    item.setIcon(R.drawable.ic_saved)
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
                    val intent = Intent (view.context, Main2Activity::class.java)
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
}