package com.example.stocktick.ui.notification

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.stocktick.R
import com.example.stocktick.room_db.entities.Notification
import java.text.SimpleDateFormat
import java.util.*

class NotificationAdapter
    constructor(
        var notifications: MutableList<Notification>,
        private val onRemoveClicked: (position: Int)->Unit,
        val context: Context
    ): RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    companion object {
        fun getInstance(
            notifications: MutableList<Notification>,
            context: Context,
            onRemoveClicked: (position: Int) -> Unit
        ) = NotificationAdapter(
            notifications = notifications,
            context = context,
            onRemoveClicked = onRemoveClicked
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.notification_title)
        val body : TextView = itemView.findViewById(
            R.id.body
        )
        val delete: ImageButton = itemView.findViewById(R.id.deleteBtn)
        val date: TextView = itemView.findViewById(R.id.dateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notification_recycler_item_layout,parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val notification = notifications[position]
        holder.title.text = notification.title
        holder.body.text = notification.body
        holder.delete.setOnClickListener{
            onRemoveClicked(
                 holder.layoutPosition
            )
        }
        val date = Date(notification.date)
        val dateString = SimpleDateFormat("dd MMM, yyyy 'at' hh:mm a").format(date)
        holder.date.text = dateString
    }

    override fun getItemCount(): Int {
       return   notifications.size
    }
}