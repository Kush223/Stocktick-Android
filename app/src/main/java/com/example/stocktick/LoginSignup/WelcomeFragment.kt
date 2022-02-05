package com.example.stocktick.LoginSignup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentWelcomePageBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomePageBinding

    private lateinit var mButtonGetStarted: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
//    https://learntodroid.com/how-to-move-between-fragments-using-the-navigation-component/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomePageBinding.inflate(layoutInflater)
        mButtonGetStarted = binding.btWelcomeGetStarted

        mButtonGetStarted.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_navigation_welcome_page_to_navigation_login_otp_)
        }
        return binding.root
//        https://stackoverflow.com/questions/63007726/navigation-component-how-to-navigate-from-activity-to-a-fragment
//        and the android documentation link:
//        https://developer.android.com/guide/navigation/navigation-navigate
    }
}