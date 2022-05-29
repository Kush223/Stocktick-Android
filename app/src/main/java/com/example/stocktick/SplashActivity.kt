package com.example.stocktick

import android.Manifest.permission.READ_SMS
import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.stocktick.auth.LoginSignupActivity
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import com.example.stocktick.utility.Constant.permSms
import com.example.stocktick.utility.Constant.smsPerm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.jar.Manifest


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val sharedPreferences: SharedPreferences = getSharedPreferences(USER, MODE_PRIVATE)
        val granted = sharedPreferences.getInt(smsPerm,22)

        lifecycleScope.launch(Dispatchers.Main){
            val backgroundImage: ImageView = findViewById(R.id.splashImage)
            val slideAnimation = AnimationUtils.loadAnimation(this@SplashActivity, R.anim.side_slide)
            backgroundImage.startAnimation(slideAnimation)
            delay(1200)

            if (ContextCompat.checkSelfPermission(this@SplashActivity, READ_SMS) != PackageManager.PERMISSION_GRANTED) {
                val dialogBuilder = AlertDialog.Builder(this@SplashActivity,R.style.AlertDialog)
                dialogBuilder.setTitle("SMS reading permission")
                dialogBuilder.setMessage("This app need sms reading permission to analyze your total monthly income and expenditure")
                dialogBuilder.setPositiveButton("GIVE PERMISSION"){dialog, which->
                    dialog.dismiss()
                    ActivityCompat.requestPermissions(this@SplashActivity,
                        arrayOf(READ_SMS),
                        permSms)
                }
                dialogBuilder.setNegativeButton("NO"){dialog, which->
                    dialog.dismiss()
                    finish()
                }
                dialogBuilder.show()

            }
            else{
                Log.d("sds", granted.toString())
                if(granted==1){
                    val token = sharedPreferences.getString(TOKEN,"")
                    if(token == ""){
                            val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
                            //opened the login signup activity.
                            //this activity now will have the welcome screen fragment.
                            startActivity(intent)
                            finish()

                    }
                    else{
                            val intent = Intent(this@SplashActivity,MainActivity::class.java)
                            //inside main activity navigation embedded all the main app functionalities.
                            startActivity(intent)
                            finish()


                    }
                }
                else{
                    Toast.makeText(this@SplashActivity,"Please give sms reading permission from settings to proceed",Toast.LENGTH_LONG).show()
                }
            }
        }




        //stackoverflow link for handlers: https://stackoverflow.com/questions/61023968/what-do-i-use-now-that-handler-is-deprecated
    }
    override fun onRequestPermissionsResult(requestCode: Int,
                                   permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val granted = if(checkPermissionGranted(requestCode, permissions, grantResults)) 1 else 0
        val sharedPreferences: SharedPreferences = getSharedPreferences(USER, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        if(granted==1){
            editor.putInt(smsPerm,1)
            editor.apply()
            val token = sharedPreferences.getString("token","a")
            if(token == "a"){
                    val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
                    //opened the login signup activity.
                    //this activity now will have the welcome screen fragment.
                    startActivity(intent)
                    finish()
            }
            else{
                val intent = Intent(this@SplashActivity,MainActivity::class.java)
                //inside mainactiivty navigation embedded all the main app functionalities.
                startActivity(intent)
                finish()
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

