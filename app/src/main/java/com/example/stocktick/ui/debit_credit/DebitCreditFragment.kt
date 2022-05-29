package com.example.stocktick.ui.debit_credit

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentDebitCreditBinding
import com.example.stocktick.utility.Constant
import com.example.stocktick.utility.SmsReader
import com.razerdp.widget.animatedpieview.AnimatedPieView
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import java.time.Month


private const val TAG = "DebitCreditFragment"

class DebitCreditFragment : Fragment(R.layout.fragment_debit_credit),
    AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentDebitCreditBinding
    private lateinit var timeSpinner: Spinner

    private lateinit var pieView: AnimatedPieView

    private val lastMonthData: SmsReader.FinanceData =
        SmsReader.FinanceData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)

    private val smsReader by lazy {
        SmsReader(requireContext())
    }


    private val sharedPreferences: SharedPreferences by lazy {
        requireContext().getSharedPreferences(Constant.SMS_SHARED_PREFS, Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDebitCreditBinding.bind(view)
        timeSpinner = binding.timeSpinner
        timeSpinner.onItemSelectedListener = this
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.gender_spinner_dropdown,
            smsReader.getLastSixMonthsName()
        )
        adapter.setDropDownViewResource(R.layout.gender_spinner_dropdown)
        timeSpinner.adapter = adapter



        pieView = binding.pieChartHome

        lastMonthData.credit =
            sharedPreferences.getFloat(Constant.LAST_MONTH_CREDIT, 0f).toDouble()
        lastMonthData.debit = sharedPreferences.getFloat(Constant.LAST_MONTH_DEBIT, 0f).toDouble()




    }


    private fun getConfig(
        credit: Double = 0.0,
        debit: Double = 0.0
    ): AnimatedPieViewConfig {
        return AnimatedPieViewConfig().apply {
            startAngle(90f)
            addData(
                SimplePieInfo(
                    credit,
                    Color.parseColor("#F75C1E"),
                    "Credit $credit INR"
                )
            )
            addData(
                SimplePieInfo(
                    debit,
                    Color.parseColor("#2FAF89"),
                    "Debit $debit INR"
                )
            )

            autoSize(false)
            drawText(true)
            textSize(35f)
            pieRadius(200f)
            startAngle(90f)
            duration(500)
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d(TAG, "Position :$position")
        when (position) {
            0->{
                //default
                pieView.applyConfig(
                    getConfig(
                        lastMonthData.credit,
                        lastMonthData.debit
                    )
                )
                pieView.start()

                binding.creditAmount.text = "${lastMonthData.credit} INR"
                binding.debitAmount.text = "${lastMonthData.debit} INR"
            }
            1->{
                smsReader.readMonthSms(SmsReader.MONTH_SECOND){
                    updateData(it)
                }
            }
            2->{
                smsReader.readMonthSms(SmsReader.MONTH_THIRD){
                    updateData(it)
                }
            }
            3->{
                smsReader.readMonthSms(SmsReader.MONTH_FOURTH){
                    updateData(it)
                }
            }
            4->{
                smsReader.readMonthSms(SmsReader.MONTH_FIFTH){
                    updateData(it)
                }
            }
            5->{
                smsReader.readMonthSms(SmsReader.MONTH_SIXTH){
                    updateData(it)
                }
            }

        }

    }

    private fun updateData(data: SmsReader.FinanceData) {
        pieView.applyConfig(
            getConfig(
                data.credit,
                data.debit
            )
        )
        pieView.start()
        binding.creditAmount.text = "${data.credit} INR"
        binding.debitAmount.text = "${data.debit} INR"
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }


}