package com.example.stocktick.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.example.stocktick.R
import com.example.stocktick.utility.Constant
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushNotificationService : FirebaseMessagingService() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        val title = message.notification?.title ?: ""
        val text = message.notification?.body ?: ""
        val channel =
            NotificationChannel(
                Constant.CHANNEL_ID,
                "Heads Up Notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)

        val notification = Notification.Builder(this, Constant.CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(text)
            .setSmallIcon(R.drawable.stocktick_logo)
            .setAutoCancel(true)
        NotificationManagerCompat.from(this).notify(1, notification.build())
        super.onMessageReceived(message)
    }
}