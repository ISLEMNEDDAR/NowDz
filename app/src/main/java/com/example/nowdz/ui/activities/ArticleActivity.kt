package com.example.nowdz.ui

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import com.example.nowdz.R
import com.example.nowdz.helper.onWebView
import com.example.nowdz.ui.activities.BaseActivity
import com.example.nowdz.ui.activities.MainActivity

class ArticleActivity : BaseActivity(),onWebView {
    var mywebview: WebView? = null
    private var backarrow : ImageView?=null
    private var home : ImageView?=null
    private var setting : ImageView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        mywebview = findViewById(R.id.web_view)
        if (mywebview == null) { print("mWebView is null")}
        if (mywebview!!.settings == null) { print("Settings is null")}
        mywebview!!.loadUrl("https://www.tsa-algerie.com")
        // Enable Javascript
        val webSettings = mywebview!!.settings
        webSettings.javaScriptEnabled = true
        // Force links and redirects to open in the WebView instead of in a browser
        mywebview!!.webViewClient = WebViewClient()

        backarrow = findViewById(R.id.back_toolbar)
        home = findViewById(R.id.home_toolbar)
        backarrow!!.setOnClickListener{
            if ( getFragmentManager().getBackStackEntryCount() > 0)
            {
                getFragmentManager().popBackStack()
            }
            super.onBackPressed()
        }
        home!!.setOnClickListener {
            showActivity(this, MainActivity::class.java)
        }


    }
}
