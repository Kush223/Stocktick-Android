package com.example.stocktick

import android.Manifest.permission.READ_SMS
import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.stocktick.auth.LoginSignupActivity
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import com.example.stocktick.utility.Constant.permSms
import com.example.stocktick.utility.Constant.smsPerm
import java.util.jar.Manifest


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (ContextCompat.checkSelfPermission(this, READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(READ_SMS),
                    permSms)
        }
        else{
            val sharedPreferences: SharedPreferences = this.getSharedPreferences(USER, Activity.MODE_PRIVATE)
            val granted = sharedPreferences.getInt(smsPerm,0)
            //Log.d("sds", granted.toString())
            if(granted==1){
                val token = sharedPreferences.getString(TOKEN,"a")
                if(token == "a"){
                    val mainHandler =  Handler(Looper.getMainLooper())
                    mainHandler.postDelayed({
                        val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
                        //opened the login signup activity.
                        //this activity now will have the welcome screen fragment.
                        startActivity(intent)
                    }, 2000)
                }
                else{
                    val intent = Intent(this@SplashActivity,MainActivity::class.java)
                    //inside main activity navigation embedded all the main app functionalities.
                    startActivity(intent)
                }
            }
            else{
                Toast.makeText(this,"Please give sms reading permission from settings to proceed",Toast.LENGTH_LONG).show()
            }
        }

        //stackoverflow link for handlers: https://stackoverflow.com/questions/61023968/what-do-i-use-now-that-handler-is-deprecated
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                   permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val granted = if(checkPermissionGranted(requestCode, permissions, grantResults)) 1 else 0
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(USER, Activity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if(granted==1){
            editor.putInt(smsPerm,1)
            editor.apply()
            val token = sharedPreferences.getString("token","a")
            if(token == "a"){
                val mainHandler =  Handler(Looper.getMainLooper())
                mainHandler.postDelayed({
                    val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
                    //opened the login signup activity.
                    //this activity now will have the welcome screen fragment.
                    startActivity(intent)
                }, 2000)
            }
            else{
                editor.putInt(smsPerm,0)
                editor.apply()
                val intent = Intent(this@SplashActivity,MainActivity::class.java)
                //inside mainactiivty navigation embedded all the main app functionalities.
                startActivity(intent)
            }
        }
        else{
            Toast.makeText(this,"Please give sms reading permission from settings to proceed",Toast.LENGTH_LONG).show()
        }
        //Toast.makeText(this,granted, Toast.LENGTH_SHORT).show()
    }

    private fun checkPermissionGranted(requestCode: Int,
                                       permissions: Array<String>, grantResults: IntArray): Boolean{
        when (requestCode) {
            permSms -> {
                // If request is cancelled, the result arrays are empty.
                return (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            }
        }
        return false
    }
}

