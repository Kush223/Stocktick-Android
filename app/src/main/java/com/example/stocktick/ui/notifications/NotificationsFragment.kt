package com.example.stocktick.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.stocktick.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var binding: FragmentNotificationsBinding
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        val viewModelFactory = NotificationsViewModelFactory(requireContext())
        notificationsViewModel = ViewModelProvider(
            this,viewModelFactory).get(NotificationsViewModel::class.java)

        val textView = binding.textNotifications
        notificationsViewModel.mText.observe(viewLifecycleOwner, { s -> textView.text = s })
        return binding.root
        //anything non view related preferred in some other functions.
    }
}