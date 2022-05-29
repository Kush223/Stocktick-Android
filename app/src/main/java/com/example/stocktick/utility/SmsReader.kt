package com.example.stocktick.utility

import android.content.Context
import android.net.Uri
import android.provider.CalendarContract
import android.provider.Telephony
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import java.lang.NumberFormatException
import java.text.SimpleDateFormat
import java.util.*


private const val TAG = "SmsReaderLog123"


/**
 * Shared Prefs Format
 * Name: Constant.SMS_SHARED_PREFS
 * Format:
 * sync_time: Long
 * credit_last_month : Float
 * debit_last_month: Float*/

class SmsReader
constructor(
    private val context: Context
){
    private val sharedPrefs = context.getSharedPreferences(Constant.SMS_SHARED_PREFS, Context.MODE_PRIVATE)
    private val currentTime = Calendar.getInstance()



    companion object {
        //constants
        val LAST_MONTH = 11
        val MONTH_SECOND = 12
        val MONTH_THIRD = 13
        val MONTH_FOURTH = 14
        val MONTH_FIFTH = 15
        val MONTH_SIXTH = 16
        fun getInstance(
            context: Context
        ): SmsReader  = SmsReader(context)
    }

    private fun Calendar.setToStartOfMonth(){
        this.set(Calendar.DAY_OF_MONTH, 1)
        this.set(Calendar.HOUR_OF_DAY, 0)
        this.set(Calendar.MINUTE, 0)
        this.set(Calendar.SECOND, 0)
    }

    fun getLastSixMonthsName(): List<String>{
        val months = mutableListOf<String>()
        val now = Calendar.getInstance()
        for (i in 1..6){
            now.add(Calendar.MONTH, -1)
            val element = SimpleDateFormat("MMM yyyy").format(now.time)
            months.add(element)
        }
        return months
    }



    private fun getCalendarInterval(lastWhich: Int): Pair<Calendar, Calendar>{
        val mStart = Calendar.getInstance()
        val mEnd = Calendar.getInstance()

        var month = currentTime.get(Calendar.MONTH)-lastWhich
        if (month >= 0 ){
            //same year
            mStart.set(Calendar.MONTH, month)
            mStart.setToStartOfMonth()
            mEnd.set(Calendar.MONTH, month+1)
            mEnd.setToStartOfMonth()
            return Pair(mStart, mEnd)
        }
        else {
            //It is in the previous year
            month +=12 //Now it correctly corresponds to the exact month in previous year
            mStart.set(Calendar.MONTH, month)
            mStart.set(Calendar.YEAR, currentTime.get(Calendar.YEAR)-1)
            mStart.setToStartOfMonth()
            if (month == Calendar.DECEMBER) {
                mEnd.set(Calendar.MONTH, Calendar.JANUARY)
            }
            else {
                mEnd.set(Calendar.MONTH, month +1)
                mEnd.set(Calendar.YEAR, currentTime.get(Calendar.YEAR)-1)
            }
            mEnd.setToStartOfMonth()

            return  Pair(mStart, mEnd)
        }

    }

    private fun getTimeInterval(month : Int): Pair<Long, Long>{

        when (month){
            LAST_MONTH -> {
                val pair = getCalendarInterval(1)
                return Pair(pair.first.timeInMillis, pair.second.timeInMillis)

            }
            MONTH_SECOND -> {
                val pair = getCalendarInterval(2)
                return Pair(pair.first.timeInMillis, pair.second.timeInMillis)
            }
            MONTH_THIRD -> {
                val pair = getCalendarInterval(3)
                return Pair(pair.first.timeInMillis, pair.second.timeInMillis)
            }
            MONTH_FOURTH -> {
                val pair = getCalendarInterval(4)
                return Pair(pair.first.timeInMillis, pair.second.timeInMillis)
            }
            MONTH_FIFTH->{
                val pair = getCalendarInterval(5)
                return Pair(pair.first.timeInMillis, pair.second.timeInMillis)
            }
            MONTH_SIXTH-> {
                val pair = getCalendarInterval(6)
                return Pair(pair.first.timeInMillis, pair.second.timeInMillis)
            }
            else -> {
                return Pair(-1,-1)
            }
        }
    }


    fun readMonthSms(
        month: Int,
        onResponse: (data: FinanceData) -> Unit
    ){
        GlobalScope.launch(Dispatchers.IO){
            try {
                val financeData = FinanceData(0.0,0.0,0.0,0.0,0.0,0.0)
                val cursor = context?.contentResolver?.query(Uri.parse("content://sms/inbox"), null, null, null, null)
                val timePair = getTimeInterval(month)
                if (cursor != null) {
                    if (cursor.moveToFirst()) { // must check the result to prevent exception
                        do {
                            var msgData = ""
                            for (idx in 0 until cursor.columnCount) {
                                val smsDate = cursor.getString(cursor.getColumnIndexOrThrow(
                                    Telephony.Sms.DATE))
                                val number = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                                val body = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))

                                if (smsDate.toLong() > timePair.second || smsDate.toLong() < timePair.first) continue



                                msgData += body
                            }

                            if (msgData.contains("debited")){
                                //its a debit message
                                Log.d(TAG, "Debit message :$msgData")
                                var i = msgData.indexOf("Rs")+2
                                val amount = StringBuffer()
                                var char = msgData[i]
                                do {
                                    if (char != ',')
                                    amount.append(char)
                                    i++
                                    char = msgData[i]
                                } while(char != ' ')
                                Log.d(TAG, "amount filtered is :$amount/-")
                                try {
                                    financeData.debit+=amount.toString().toDouble()
                                    if (msgData.contains("UPI")) financeData.debitUPI+=amount.toString().toDouble()
                                } 
                                catch (e: NumberFormatException){
                                    Log.e(TAG, "Number format exception, number is :$amount/-", )
                                }
                            }
                            else if (msgData.contains("credited")){
                                Log.d(TAG, "Credit message: $msgData")
                            }
                        } while (cursor.moveToNext())
                        Log.d(TAG, "Debit amount is :${financeData.debit}")
                        onResponse(
                            financeData
                        )
                        if (month == LAST_MONTH){
                            val editor = sharedPrefs.edit()
                            editor.putFloat(Constant.LAST_MONTH_CREDIT, financeData.credit.toFloat())
                            editor.putFloat(Constant.LAST_MONTH_DEBIT, financeData.debit.toFloat())
                            editor.putLong(Constant.SYNC_TIME, currentTime.timeInMillis)
                            editor.apply()
                        }
                    } else {
                        Log.d(TAG, "SMS box is empty")
                    }
                }
                cursor?.close()
            }
            catch (e: Exception){

            }
        }
    }

    data class FinanceData(
        var credit: Double,
        var debit: Double,
        var creditAxisBank: Double,
        var creditSBI: Double,
        var creditHDFC: Double,
        var debitUPI: Double
    )
}