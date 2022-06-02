package com.example.stocktick.room_db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notification")
data class Notification(
    @PrimaryKey(autoGenerate = true) val uid: Int=0,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Body") val body: String,
    @ColumnInfo(name = "Date") val date: Long
)
