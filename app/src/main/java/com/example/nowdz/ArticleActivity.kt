package com.example.nowdz

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.example.nowdz.helper.onWebView

class ArticleActivity : BaseActivity(),onWebView {
    var mywebview: WebView? = null
    private var backarrow : ImageView?=null
    private var home : ImageView?=null
    private var setting : ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        mywebview = findViewById(com.example.nowdz.R.id.web_view) as WebView
        if (mywebview == null) { print("mWebView is null")}
        if (mywebview!!.getSettings() == null) { print("Settings is null")}
        mywebview!!.loadUrl("https://www.tsa-algerie.com")
        // Enable Javascript
        val webSettings = mywebview!!.getSettings()
        webSettings.setJavaScriptEnabled(true)
        // Force links and redirects to open in the WebView instead of in a browser
        mywebview!!.setWebViewClient(WebViewClient())

        backarrow = findViewById(R.id.back_toolbar)
        home = findViewById(R.id.home_toolbar)
        setting = findViewById(R.id.menu_toolbar)
        backarrow!!.setOnClickListener{
            if ( getFragmentManager().getBackStackEntryCount() > 0)
            {
                getFragmentManager().popBackStack()
            }
            super.onBackPressed()
        }
        home!!.setOnClickListener {
            showActivity(this,MainActivity::class.java)
        }
        setting!!.setOnClickListener {
            showActivity(this,SettingsActivity::class.java)
        }

    }
}
