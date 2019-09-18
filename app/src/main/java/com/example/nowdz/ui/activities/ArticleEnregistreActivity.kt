package com.example.nowdz.ui.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nowdz.MAX_SMS_MESSAGE_LENGTH
import com.example.nowdz.ui.Adapter.ArticleEnregistreAdapter
import com.example.nowdz.R
import com.example.nowdz.SEND_SMS_PERMISSION_REQUEST_CODE
import com.example.nowdz.helper.RecycleViewHelper
import com.example.nowdz.model.Article
import com.example.nowdz.viewModel.ArticleViewModel

const val currentView = R.layout.activity_article_enregistre
class ArticleEnregistreActivity : BaseActivity(), RecycleViewHelper {
    override var itemRecycleView: androidx.recyclerview.widget.RecyclerView? = null
    private var articleList = ArrayList<Article>()
    private var articleAdapter : ArticleEnregistreAdapter? = null
    private lateinit var articleViewModel: ArticleViewModel
    private var backarrow : ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(currentView)
        backarrow = findViewById(R.id.back_toolbar)
        backarrow!!.setOnClickListener{
            if ( getFragmentManager().backStackEntryCount > 0)
            {
                getFragmentManager().popBackStack()
            }
            super.onBackPressed()

        }
        val view = findViewById<View>(android.R.id.content)
        init(view)
    }

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
                        arrayOf(android.Manifest.permission.SEND_SMS), SEND_SMS_PERMISSION_REQUEST_CODE
                    )

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

    private fun init(v : View){
        articleAdapter = ArticleEnregistreAdapter(v.context,v,this@ArticleEnregistreActivity)
        initLineaire(v, R.id.recycleview_enregistre,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,articleAdapter as androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>)
        articleViewModel = ViewModelProviders.of(this).get(
            ArticleViewModel::class.java
        )
        articleViewModel.getRestArticle().observe(
            this,
            Observer {
                articleAdapter!!.setArticles(it!!)
            }
        )

    }



}
