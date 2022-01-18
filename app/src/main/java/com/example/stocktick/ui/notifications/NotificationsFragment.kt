package com.example.stocktick.ui.notifications

import androidx.lifecycle.ViewModelProvider.get
import com.example.stocktick.ui.notifications.NotificationsViewModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.stocktick.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {
    private var notificationsViewModel: NotificationsViewModel? = null
    private var binding: FragmentNotificationsBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel = ViewModelProvider(this).get(
            NotificationsViewModel::class.java
        )
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding!!.root
        val textView = binding!!.textNotifications
        notificationsViewModel!!.text.observe(viewLifecycleOwner, { s -> textView.text = s })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}