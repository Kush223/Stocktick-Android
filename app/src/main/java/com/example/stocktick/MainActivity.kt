package com.example.stocktick

import android.app.Activity
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.ActivityMainBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.utility.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    //    https://medium.com/androiddevelopers/appcompat-v23-2-daynight-d10f90c83e94
    lateinit var binding: ActivityMainBinding
    lateinit var navView: BottomNavigationView
    lateinit var drawerNavView: NavigationView
    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
    }


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerNavView = binding.navView



        setSupportActionBar(binding.topAppBar)
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);

                if (cursor != null) {
                    if (cursor.moveToFirst()) { // must check the result to prevent exception
                        do {
                            var msgData = ""
                            for (idx in 0 until cursor.columnCount) {
                                val smsDate = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE));
                                val number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
                                val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
                                //val dateFormat= Date(smsDate)
//                        val type
//                        switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
//                            case Telephony.Sms.MESSAGE_TYPE_INBOX:
//                            type = "inbox";
//                            break;
//                            case Telephony.Sms.MESSAGE_TYPE_SENT:
//                            type = "sent";
//                            break;
//                            case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
//                            type = "outbox";
//                            break;
//                            default:
//                            break;
//                        }
                                msgData += body
                            }
                            Log.d("msg", msgData)
                            // use msgData
                        } while (cursor.moveToNext())
                    } else {
                        // empty box, no SMS
                    }
                }
            }
            catch (e: Exception){

            }

        }



        navView = binding.bottomNavigationView
        binding.bottomNavigationView.background = null

        navView.selectedItemId = R.id.navigation_insurance

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.navigation_mutual_funds,
            R.id.navigation_insurance, R.id.navigation_home, R.id.navigation_loan, R.id.navigation_education
        )
            .setOpenableLayout(binding.container)
            .build()

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
//        navController = navHostFragment.navController
        //val navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        NavigationUI.setupWithNavController(navView, navController)
        drawerNavView.setupWithNavController(navController)

        updateDrawerProfile()

    }

    private fun updateDrawerProfile() {
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val sharedPreferences: SharedPreferences =
                   getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
                val tokenSharedPreference =
                    sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
                val response = RetrofitClientInstance.retrofitService.getUserDetails(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful){
                    withContext(Dispatchers.Main) {
                        val body = response.body() ?: return@withContext
                        val drawerHeaderView = drawerNavView.getHeaderView(0)
                        val tvName: TextView = drawerHeaderView.findViewById(R.id.tvUserName)
                        val tvPhoneNo: TextView = drawerHeaderView.findViewById(R.id.tvPhoneNo)
                        val ivProfile: ImageView = drawerHeaderView.findViewById(R.id.ivProfilePic)
                        tvName.text = body.name ?: "No Name"
                        tvPhoneNo.text = body.phone
                        Glide.with(this@MainActivity).load(body.profile_url).into(ivProfile)
                    }
                }
            } catch (e: Exception){

            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.container) || super.onSupportNavigateUp()
    }



//    override fun onResume() {
//        super.onResume()
//        val cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
//
//        if (cursor != null) {
//            if (cursor.moveToFirst()) { // must check the result to prevent exception
//                do {
//                    var msgData = ""
////                    for(idx in 0 until cursor.columnCount)
////                    {
//                    val smsDate = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE));
//                    val number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS));
//                    val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY));
//                    //val dateFormat= Date(smsDate)
////                        val type
////                        switch (Integer.parseInt(c.getString(c.getColumnIndexOrThrow(Telephony.Sms.TYPE)))) {
////                            case Telephony.Sms.MESSAGE_TYPE_INBOX:
////                            type = "inbox";
////                            break;
////                            case Telephony.Sms.MESSAGE_TYPE_SENT:
////                            type = "sent";
////                            break;
////                            case Telephony.Sms.MESSAGE_TYPE_OUTBOX:
////                            type = "outbox";
////                            break;
////                            default:
////                            break;
////                        }
//                    msgData += body
////                    }
//                    Log.d("msg", msgData)
//                    //Log.d("msg","end")
//                    // use msgData
//                } while (cursor.moveToNext())
//                cursor.close()
//            } else {
//                // empty box, no SMS
//            }
//        }
//    }
}