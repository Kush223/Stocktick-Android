package com.example.stocktick.utility

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class UtilsService(val context: Context) {
    private val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(Constant.USER, Context.MODE_PRIVATE)

    fun logout(){
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(Constant.TOKEN, "")
        editor.apply()

        Toast.makeText(context,"Succesfully Logged Out", Toast.LENGTH_SHORT).show()
    }

}