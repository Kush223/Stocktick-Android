package com.example.stocktick.ui.debit_credit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Pie
import com.example.stocktick.databinding.FragmentDebitCreditBinding

class DebitCreditFragment : Fragment(){
    private lateinit var binding: FragmentDebitCreditBinding
    private lateinit var anyChartView : AnyChartView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //pie chart
        anyChartView= binding.pieChartHome
        val pie : Pie = AnyChart.pie()
        val list = ArrayList<DataEntry>()
        list.add(ValueDataEntry("credit",30))
        list.add(ValueDataEntry("debit",10))
        pie.data(list)
        pie.labels().position("left")
        pie.legend().position("left")
        pie.legend().align("center")
        pie.legend().itemsLayout("verticalExpandable")
        pie.background().fill("#222222")
        pie.credits().enabled(false)
        anyChartView.setChart(pie)



    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDebitCreditBinding.inflate(inflater, container, false)
        return binding.root
    }
}