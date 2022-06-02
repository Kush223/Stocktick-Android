package com.example.stocktick.ui.notification

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.MainActivity
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentNotificationBinding


private const val TAG = "NotificationFragment"
class NotificationFragment : Fragment(R.layout.fragment_notification) {
    private lateinit var binding: FragmentNotificationBinding
    private val viewModel: NotificationViewModel by activityViewModels()

    private lateinit var notificationRecyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.refreshNotifications()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNotificationBinding.bind(view)
        notificationRecyclerView = binding.recyclerNotifications
        notificationRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = NotificationAdapter.getInstance(
            mutableListOf(),
            requireContext()
        ){ deletePosition->
            try {
                viewModel.deleteNotification(adapter.notifications[deletePosition])
                adapter.notifications.removeAt(deletePosition)

            } catch (e: IndexOutOfBoundsException){
                Log.e(TAG, "onViewCreated: Index out of bound :${e.localizedMessage}")
            }
            adapter.notifyItemRemoved(deletePosition)
        }
        notificationRecyclerView.adapter = adapter

        viewModel.notifications.observe(requireActivity()){
            Log.d(TAG, "Notification observed :$it")
            adapter.notifications = it.toMutableList()
            adapter.notifyDataSetChanged()
            if (it.isNotEmpty()){
                binding.noNotifications.visibility = View.GONE
            }
        }


        (activity as MainActivity).binding.layoutBottomNeumorph.visibility  = View.GONE
    }
}