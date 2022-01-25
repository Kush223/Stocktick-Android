package com.example.stocktick

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.stocktick.R
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.stocktick.LoginSignup.LoginSignupActivity

//Splash screens are typically used by particularly
// large applications to notify the user that the program is in the process of loading.
// They provide feedback that a lengthy process is underway.
// Occasionally, a progress bar within the splash screen indicates the loading progress

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("USER", Activity.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","a")
        //Log.d("bb",token!!)
        if(token == "a"){
            val mainHandler =  Handler(Looper.getMainLooper())
            mainHandler.postDelayed({
                val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
                startActivity(intent)
            }, 2000)
        }
        else{
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            startActivity(intent)
        }

        //stackoverflow link for handlers: https://stackoverflow.com/questions/61023968/what-do-i-use-now-that-handler-is-deprecated

    }
}