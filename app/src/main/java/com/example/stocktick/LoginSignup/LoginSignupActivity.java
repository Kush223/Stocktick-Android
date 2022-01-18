package com.example.stocktick.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.stocktick.R;
import com.example.stocktick.databinding.ActivityLoginSignupBinding;

public class LoginSignupActivity extends AppCompatActivity {
    private ActivityLoginSignupBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);
        getSupportFragmentManager().beginTransaction().add(R.id.login_signup_activity,
                new LoginFragment()).commit();
        binding = ActivityLoginSignupBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}