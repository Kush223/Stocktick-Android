package com.example.stocktick

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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
import com.example.stocktick.utility.SmsReader
import com.example.stocktick.utility.UtilsService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{
    //    https://medium.com/androiddevelopers/appcompat-v23-2-daynight-d10f90c83e94
    lateinit var binding: ActivityMainBinding
    lateinit var navView: BottomNavigationView
    lateinit var drawerNavView: NavigationView
    private lateinit var utilsService: UtilsService
    private val navController by lazy {
        Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
    }

    private val topLevelDestinations = setOf(R.id.navigation_mutual_funds,
        R.id.navigation_insurance, R.id.navigation_home, R.id.navigation_loan, R.id.navigation_education)


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drawerNavView = binding.navView
        utilsService = UtilsService(this)


        FirebaseMessaging.getInstance().subscribeToTopic("all")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
            }



        setSupportActionBar(binding.topAppBar)





        navView = binding.bottomNavigationView
        binding.bottomNavigationView.background = null

        navView.selectedItemId = R.id.navigation_insurance

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(R.id.navigation_mutual_funds,
                R.id.navigation_insurance, R.id.navigation_home, R.id.navigation_loan, R.id.navigation_education),

            binding.container
        )


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
        readSMS()



    }

    private fun readSMS() {

        val smsReader = SmsReader.getInstance(this)
        smsReader.readMonthSms(SmsReader.LAST_MONTH){

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
        if (navController.currentDestination?.let { topLevelDestinations.contains(it.id) } == true) {
            binding.container.openDrawer(GravityCompat.START)
            return true
        }

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
                navController.navigate(returnId(1))
            }
            R.id.navigation_notifications ->{

                navController.navigate(returnId(2))
            }
            R.id.viewProfile ->{
                navController.navigate(returnId(3))
            }
            R.id.logout ->{
                utilsService.logout()
                finish()
            }

        }
        binding.container.closeDrawer(GravityCompat.START)
        return true
    }

    /**
     * 1 Debit Credit fragment
     * 2 Notification Fragment
     * 3 View Profile Fragment*/
    private fun returnId(which  : Int) : Int {
        when (navController.currentDestination?.id) {
            R.id.navigation_home -> {
                when (which){
                    1 -> return R.id.action_navigation_home_to_debitCreditFragment
                    2 -> return R.id.action_navigation_home_to_notificationFragment
                    3 -> return R.id.action_navigation_home_to_viewProfile
                }
            }

            R.id.navigation_loan -> {
                when (which){
                    1 -> return R.id.action_navigation_loan_to_debitCreditFragment
                    2 -> return R.id.action_navigation_loan_to_notificationFragment
                    3 -> return R.id.action_navigation_loan_to_viewProfile
                }
            }
            R.id.navigation_education -> {
                when (which){
                    1 -> return R.id.action_navigation_education_to_debitCreditFragment
                    2 -> return R.id.action_navigation_education_to_notificationFragment
                    3 -> return R.id.action_navigation_education_to_viewProfile
                }
            }
            R.id.navigation_insurance -> {
                when (which){
                    1 -> return R.id.action_navigation_insurance_to_debitCreditFragment
                    2 -> return R.id.action_navigation_insurance_to_notificationFragment
                    3 -> return R.id.action_navigation_insurance_to_viewProfile
                }
            }
            R.id.navigation_mutual_funds -> {
                when (which){
                    1 -> return R.id.action_navigation_mutual_funds_to_debitCreditFragment
                    2 -> return R.id.action_navigation_mutual_funds_to_notificationFragment
                    3 -> return R.id.action_navigation_mutual_funds_to_viewProfile
                }
            }
        }
        return 1

    }

}