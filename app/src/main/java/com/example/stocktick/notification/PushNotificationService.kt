package com.example.stocktick.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.example.stocktick.R
import com.example.stocktick.room_db.AppDatabase
import com.example.stocktick.utility.Constant
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

private const val TAG = "PushNotificationService"
class PushNotificationService : FirebaseMessagingService() {



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {

        Log.d(TAG, "onMessageReceived: message :$message")
        val title = message.data?.get("title") ?: ""
        val text = message.data?.get("body") ?: ""
        val channel =
            NotificationChannel(
                Constant.CHANNEL_ID,
                "Heads Up Notification",
                NotificationManager.IMPORTANCE_HIGH
            )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification = Notification.Builder(this, Constant.CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.notif_icon)

        NotificationManagerCompat.from(this).notify(0, notification.build())


        GlobalScope.launch(Dispatchers.Main) {
            try {
                Log.d(TAG, "onMessageReceived: Inside golbal scope")
                val db = AppDatabase.getAppDatabase(applicationContext)
                db?.notifDao()?.insert(
                    com.example.stocktick.room_db.entities.Notification(
                        title = title,
                        body = text,
                        date = Calendar.getInstance().timeInMillis
                    )
                )
            } catch (e: Exception) {

            }
        }
        super.onMessageReceived(message)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }
}