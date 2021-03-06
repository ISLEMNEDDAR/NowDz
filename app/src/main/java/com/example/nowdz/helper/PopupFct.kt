package com.example.nowdz.helper

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import androidx.core.content.ContextCompat
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.nowdz.MAX_SMS_MESSAGE_LENGTH
import com.example.nowdz.R
import com.example.nowdz.controller.ArticleController
import com.example.nowdz.model.Article
import com.example.nowdz.ui.ArticleActivity

class PopupFct(val context: Context,
               val view: View,
               val activity: AppCompatActivity,
               var article : Article
) : PopupMenu(context, view),GlobalHelper {
     fun onCLick() {
         setOnMenuItemClickListener {item ->
             ArticleController.currentUrl(article.url!!)
             ArticleController.currentTitle(article.titre!!)
            when (item.itemId) {
                R.id.menu_popup_share -> {

                    val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
                    activity.startActivityForResult(intent, 1)

                    Toast.makeText(context, "Showing Share Toast!", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.menu_popup_share_mail -> {

                    sendEmail()
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





    fun sendEmail() {

        Log.i("Send email", "")

        val TO = arrayOf("")
        val CC = arrayOf("")
        val emailbody = ArticleController.getCurrentUrl()
        val emailtitle = "DzNow : ${ArticleController.getCurrentTitle()}"
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO)
        emailIntent.putExtra(Intent.EXTRA_CC, CC)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,emailtitle)
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailbody)

        try {
            activity.startActivity(Intent.createChooser(emailIntent, "Send mail..."))
            activity.finish()
            Log.i("Finished sending email", "")
        } catch (ex: android.content.ActivityNotFoundException) {
            Toast.makeText(
                context,
                "There is no email client installed.", Toast.LENGTH_SHORT
            ).show()
        }

    }
      fun inflat(id : Int){
         this.inflate(R.menu.menu_popup)

         try {
             val fieldMPopup = androidx.appcompat.widget.PopupMenu::class.java.getDeclaredField("mPopup")
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