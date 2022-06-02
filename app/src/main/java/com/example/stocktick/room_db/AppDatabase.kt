package com.example.stocktick.room_db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.stocktick.room_db.entities.Notification
import com.example.stocktick.utility.Constant


@Database(entities = [Notification::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun    notifDao(): NotifDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        open fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, Constant.DB_NAME
                ).allowMainThreadQueries().build()

                //Room.inMemoryDatabaseBuilder(context.getApplicationContext(),AppDatabase.class).allowMainThreadQueries().build();
            }
            return INSTANCE
        }
    }

}