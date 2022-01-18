package com.example.stocktick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stocktick.R
import android.content.Intent
import android.os.Handler
import com.example.stocktick.LoginSignup.LoginSignupActivity
//UPDATE HANDLERS to kotlin
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}