package com.example.nowdz.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.nowdz.R
import com.example.nowdz.ui.activities.MainActivity

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        val handler = Handler()
        handler.postDelayed({
            // do something
            val intent = Intent(this@FirstActivity, MainActivity::class.java)
            // If you just use this that is not a valid context. Use ActivityName.this
            startActivity(intent)
            this@FirstActivity.finish()
        }, 3000)
    }
}
