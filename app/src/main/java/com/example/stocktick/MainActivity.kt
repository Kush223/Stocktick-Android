package com.example.stocktick

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.view.MenuItem
import android.view.WindowManager
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
import com.example.stocktick.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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