package com.example.stocktick.ui.notification

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.stocktick.room_db.AppDatabase
import com.example.stocktick.room_db.entities.Notification
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private const val TAG = "NotificationViewModel"
class NotificationViewModel(application: Application) : AndroidViewModel(application) {
    val notifications : MutableLiveData<MutableList<Notification>> by lazy {
        MutableLiveData<MutableList<Notification>>()
    }
    private val db : AppDatabase = Room.databaseBuilder(
        getApplication(),
        AppDatabase::class.java,
        Constant.DB_NAME
    ).build()

    init {
        refreshNotifications()
    }

    fun refreshNotifications(){
        viewModelScope.launch(Dispatchers.Main){
            try {

                notifications.value = db.notifDao().getAllNotification().toMutableList()
            } catch (e: Exception){
                Log.e(TAG, "refreshNotifications: Exception :${e.localizedMessage}", )
            }
        }
    }
    
    fun deleteNotification(
        notification: Notification
    ){
        viewModelScope.launch(Dispatchers.Default){
            try {
                db.notifDao().delete(notification)
            } catch (e: Exception){
                Log.e(TAG, "deleteNotification: :${e.localizedMessage}", )
            }
        }
    }

}