package com.example.stocktick

import android.Manifest.permission.READ_SMS
import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.stocktick.auth.LoginSignupActivity
import com.example.stocktick.utility.Constant.TOKEN
import com.example.stocktick.utility.Constant.USER
import com.example.stocktick.utility.Constant.permSms
import com.example.stocktick.utility.Constant.smsPerm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
const val LOGIN = 1
const val MAIN = 2

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = getSharedPreferences(USER, MODE_PRIVATE)
        val granted = sharedPreferences.getInt(smsPerm,22)

        when (jump(granted)){
            LOGIN ->{
                lifecycleScope.launch(Dispatchers.Main){
                    delay(3000)
                    val intent = Intent(this@SplashActivity, LoginSignupActivity::class.java)
                    startActivity(intent)
                    finish()
                }

            }
            MAIN ->{

                lifecycleScope.launch(Dispatchers.Main){
                    delay(3500)
                    val intent = Intent(this@SplashActivity,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
        try {
            val gifView : ImageView = findViewById(R.id.gifView)
            Glide.with(this).load(R.mipmap.vid_splash).into(gifView)
        } catch (ex: Exception) {
        }


    }

    private fun jump(granted: Int): Int{

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
                dialogBuilder.setNegativeButton("NO"){ dialog, _ ->
                    dialog.dismiss()
                    finish()
                }
                dialogBuilder.show()

            }
            else{
                Log.d("sds", granted.toString())
                if(granted==1){
                    val token = sharedPreferences.getString(TOKEN,"")
                    return if(token == ""){
                        LOGIN
                    } else{
                        MAIN
                    }
                }
                else{
                    Toast.makeText(this@SplashActivity,"Please give sms reading permission from settings to proceed",Toast.LENGTH_LONG).show()
                }
            }
        return 0


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
                    startActivity(intent)
                    finish()
            }
            else{
                val intent = Intent(this@SplashActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        else{
            Toast.makeText(this,"Please give sms reading permission from settings to proceed",Toast.LENGTH_LONG).show()
        }
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

