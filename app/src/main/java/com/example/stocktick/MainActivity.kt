package com.example.stocktick

import android.app.Activity
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.provider.Telephony
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.stocktick.databinding.ActivityMainBinding
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.get_assistant.GetAssistantDialog
import com.example.stocktick.utility.Constant
import com.example.stocktick.utility.UtilsService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    //    https://medium.com/androiddevelopers/appcompat-v23-2-daynight-d10f90c83e94
    lateinit var binding: ActivityMainBinding
    lateinit var navView: BottomNavigationView
    lateinit var drawerNavView: NavigationView
    private lateinit var utilsService: UtilsService
    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
    }


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerNavView = binding.navView
        utilsService = UtilsService(this)



        setSupportActionBar(binding.topAppBar)
        lifecycleScope.launch(Dispatchers.IO){
            try {
                val cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null)

                if (cursor != null) {
                    if (cursor.moveToFirst()) { // must check the result to prevent exception
                        do {
                            var msgData = ""
                            for (idx in 0 until cursor.columnCount) {
                                val smsDate = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
                                val number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                                val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))
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
                cursor?.close()
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


        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)

        NavigationUI.setupWithNavController(navView, navController)
        drawerNavView.setupWithNavController(navController)

        updateDrawerProfile()
        drawerNavView.setNavigationItemSelectedListener(this)

        binding.getAssistantFab.setOnClickListener{
            GetAssistantDialog().show(
                supportFragmentManager,
                GetAssistantDialog.TAG
            )
        }


    }

    fun updateDrawerProfile() {
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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.navigation_home -> navView.selectedItemId = R.id.navigation_home
            R.id.navigation_insurance -> navView.selectedItemId = R.id.navigation_insurance
            R.id.navigation_mutual_funds -> navView.selectedItemId = R.id.navigation_mutual_funds
            R.id.navigation_education -> navView.selectedItemId = R.id.navigation_education
            R.id.navigation_loan -> navView.selectedItemId = R.id.navigation_loan
            R.id.debitCreditFragment-> {
                navController.navigate(R.id.action_navigation_home_to_debitCreditFragment)
            }
            R.id.viewProfile ->{
                navController.navigate(R.id.action_navigation_home_to_viewProfile)
            }
            R.id.logout ->{
                utilsService.logout()
                finish()
            }

        }
        binding.container.closeDrawer(GravityCompat.START)
        return true
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