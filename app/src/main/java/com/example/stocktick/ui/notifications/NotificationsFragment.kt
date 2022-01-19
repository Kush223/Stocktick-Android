package com.example.stocktick.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.databinding.FragmentNotificationsBinding
//Add Viewmodel Factory to MVVM structures?? for all fragments?

class NotificationsFragment : Fragment() {
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        notificationsViewModel = ViewModelProvider(this).get(
            NotificationsViewModel::class.java
        )
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        val textView = binding.textNotifications
        notificationsViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })
        return binding.root


    }
}