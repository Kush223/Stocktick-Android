package com.example.stocktick.room_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stocktick.room_db.entities.Notification

@Dao
interface NotifDao {
    @Query("SELECT * FROM Notification")
    suspend fun getAllNotification(): List<Notification>
    @Delete
    suspend fun delete(notification: Notification)
    @Insert
    suspend fun insert(notification: Notification)
}