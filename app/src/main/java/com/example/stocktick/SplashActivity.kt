package com.example.stocktick

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import com.example.stocktick.auth.LoginSignupActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("USER", Activity.MODE_PRIVATE)
        val token = sharedPreferences.getString("token","a")

//        if(token == "a"){
//            val mainHandler =  Handler(Looper.getMainLooper())
//            mainHandler.postDelayed({
//                val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
//                //opened the login signup activity.
//                //this activity now will have the welcome screen fragment.
//                startActivity(intent)
//            }, 2000)
//        }
//        else{
            val intent = Intent(this@SplashActivity,MainActivity::class.java)
            //inside mainactiivty navigation embedded all the main app functionalities.
            startActivity(intent)
//        }

        //stackoverflow link for handlers: https://stackoverflow.com/questions/61023968/what-do-i-use-now-that-handler-is-deprecated

    }
}