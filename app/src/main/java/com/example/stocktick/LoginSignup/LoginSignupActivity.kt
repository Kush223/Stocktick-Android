package com.example.stocktick.LoginSignup

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.ActivityLoginSignupBinding

//TODO : Change fragment transactions to navgraph format
class LoginSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginSignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_signup)
        supportFragmentManager.beginTransaction().add(
            R.id.login_signup_activity,
            LoginFragment()
        ).commit()
        ///have to remove this i believe.....



        binding = ActivityLoginSignupBinding.inflate(
            layoutInflater
        )
        val view: View = binding.root
        setContentView(view)
    }
}