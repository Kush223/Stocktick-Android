package com.example.stocktick.ui.mutual_funds.stressed_about_finance.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.Stroke
import com.example.stocktick.R
import com.example.stocktick.databinding.FragmentPge4dot1Binding
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.MainViewModel
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.PostAsset
import com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.PostAssetData
import kotlinx.coroutines.*


private const val TAG = "Page4dot1"
class Page4dot1 : Fragment(R.layout.fragment_pge4dot1) {

    private lateinit var binding  : FragmentPge4dot1Binding

    private lateinit var chart : AnyChartView
    private val viewModel : MainViewModel by activityViewModels()

    private lateinit var assetLine : Line
    private lateinit var liabilityLine : Line
    private lateinit var netWorthLine : Line

    // assets
    private lateinit var assetYear1 : EditText
    private lateinit var assetYear2 : EditText
    private lateinit var assetYear3 : EditText
    private lateinit var assetYear4 : EditText
    private lateinit var assetYear5 : EditText
    //liabilities
    private lateinit var liaYear1 : EditText
    private lateinit var liaYear2 : EditText
    private lateinit var liaYear3 : EditText
    private lateinit var liaYear4 : EditText
    private lateinit var liaYear5 : EditText
    //years
    private lateinit var year1 : TextView
    private lateinit var year2 : TextView
    private lateinit var year3 : TextView
    private lateinit var year4 : TextView
    private lateinit var year5 : TextView


    private var job : Job? = null

    private var dates = mutableListOf<Pair<String, String>>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPge4dot1Binding.bind(view)
        initialize()
        initializeDates()
        chart = binding.anyLineChart

        try {

            setUpChart()
        } catch (e : Exception){
            Log.d(TAG, "onViewCreated: Error :${e.localizedMessage}")
        }
        fillData()
        responder()

        binding.btNext.setOnClickListener{
            postData {
                view.findNavController().navigate(R.id.action_page4dot1_to_page5)
            }
        }
        binding.btSkip.setOnClickListener{
            view.findNavController().navigate(R.id.action_page4dot1_to_page5)
        }
    }

    private fun initialize(){
        assetYear1 = binding.etAssetYear1
        assetYear2 = binding.etAssetYear2
        assetYear3 = binding.etAssetYear3
        assetYear4 = binding.etAssetYear4
        assetYear5 = binding.etAssetYear5

        liaYear1 = binding.etLiaYear1
        liaYear2 = binding.etLiaYear2
        liaYear3 = binding.etLiaYear3
        liaYear4 = binding.etLiaYear4
        liaYear5 = binding.etLiaYear5

        year1 = binding.year1
        year2 = binding.year2
        year3 = binding.year3
        year4 = binding.year4
        year5 = binding.year5

    }

    private fun initializeDates(){
        val calendar = java.util.Calendar.getInstance()
        var currentYear = calendar.get(java.util.Calendar.YEAR)
        currentYear--
        for ( i in 1 .. 5){
            dates.add(
                Pair(
                    currentYear.toString(),
                    "$currentYear - ${currentYear%100 +1 }"
                )
            )
            currentYear --
        }
        Log.d(TAG, "initializeDates: dates are $dates")

        year1.text = dates[0].second
        year2.text = dates[1].second
        year3.text = dates[2].second
        year4.text = dates[3].second
        year5.text = dates[4].second

    }



    private fun responder(){
        assetYear1.changeListener()
        assetYear2.changeListener()
        assetYear3.changeListener()
        assetYear4.changeListener()
        assetYear5.changeListener()

        liaYear1.changeListener()
        liaYear2.changeListener()
        liaYear3.changeListener()
        liaYear4.changeListener()
        liaYear5.changeListener()

    }
    private fun EditText.changeListener(){
        this.addTextChangedListener (
            onTextChanged = { text, start, before, count ->
                if (job != null) job?.cancel()
                job = lifecycleScope.launch(Dispatchers.Default) {
                    delay(500)
                    withContext(Dispatchers.Main) {
                        if (text.toString().isEmpty()) return@withContext
                        updateAssetLines()
                    }
                }
            }
        )
    }

    private fun fillData(){
        viewModel.getPage8 { isSuccessful, page8Dto ->
            Log.d(TAG, "fillData: It came back")
            if (!isSuccessful || page8Dto== null) return@getPage8
            Log.d(TAG, "fillData: it is successful")
            for (data in page8Dto.data){
                when (data.year){
                    dates[0].first ->{
                        assetYear1.setText(data.asset.toString())
                        liaYear1.setText(data.liability.toString())
                    }
                    dates[1].first ->{
                        assetYear2.setText(data.asset.toString())
                        liaYear2.setText(data.liability.toString())
                    }
                    dates[2].first ->{
                        assetYear3.setText(data.asset.toString())
                        liaYear3.setText(data.liability.toString())
                    }
                    dates[3].first ->{
                        assetYear4.setText(data.asset.toString())
                        liaYear4.setText(data.liability.toString())
                    }
                    dates[4].first ->{
                        assetYear5.setText(data.asset.toString())
                        liaYear5.setText(data.liability.toString())
                    }
                }
            }
            updateAssetLines()
        }
    }

    private fun postData(
        response : ()-> Unit
    ){
        val asset1 = assetYear1.text.toString().toLongNumber()
        val asset2 = assetYear2.text.toString().toLongNumber()
        val asset3 = assetYear3.text.toString().toLongNumber()
        val asset4 = assetYear4.text.toString().toLongNumber()
        val asset5 = assetYear5.text.toString().toLongNumber()
        val lia1 = liaYear1.text.toString().toLongNumber()
        val lia2 = liaYear2.text.toString().toLongNumber()
        val lia3 = liaYear3.text.toString().toLongNumber()
        val lia4 = liaYear4.text.toString().toLongNumber()
        val lia5 = liaYear5.text.toString().toLongNumber()
        val assetData = PostAssetData(
            listOf(
                        PostAsset(dates[0].first,  asset1, lia1, asset1 - lia1),
                PostAsset(dates[1].first, asset2, lia2, asset2 - lia2),
                PostAsset(dates[2].first, asset3, lia3, asset3 - lia3),
                PostAsset(dates[3].first, asset4, lia4, asset4 - lia4),
                PostAsset(dates[4].first, asset5, lia5, asset5 - lia5)
            )
        )
        viewModel.postPage8(
            assetData
        ){
            if (!it){
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show()
            }
            response()
        }
    }

    private fun String.toLongNumber() : Long{

        return try {
            val res = this.toLong()
            res
        } catch (e : NumberFormatException){
            0
        }
    }



    private fun setUpChart(){
        val cartesian: Cartesian = AnyChart.line()

        cartesian.animation(true)

        cartesian.padding(10.0, 20.0, 5.0, 20.0)

        cartesian.crosshair().enabled(true)
        cartesian.crosshair()
            .yLabel(true)
            .yStroke(null as Stroke?, null, null, null as String?, null as String?)

        cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

        cartesian.title("Your last 5 years net worth chart.")

        cartesian.yAxis(0).title("Value in Rupees")
        cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

        val seriesData: MutableList<DataEntry> = ArrayList()
        val asset1 = assetYear1.text.toString().toLongNumber()
        val asset2 = assetYear2.text.toString().toLongNumber()
        val asset3 = assetYear3.text.toString().toLongNumber()
        val asset4 = assetYear4.text.toString().toLongNumber()
        val asset5 = assetYear5.text.toString().toLongNumber()
        val lia1 = liaYear1.text.toString().toLongNumber()
        val lia2 = liaYear2.text.toString().toLongNumber()
        val lia3 = liaYear3.text.toString().toLongNumber()
        val lia4 = liaYear4.text.toString().toLongNumber()
        val lia5 = liaYear5.text.toString().toLongNumber()
        seriesData.add(CustomDataEntry(dates[0].first,  asset1, lia1, asset1 - lia1))
        seriesData.add(CustomDataEntry(dates[1].first, asset2, lia2, asset2 - lia2))
        seriesData.add(CustomDataEntry(dates[2].first, asset3, lia3, asset3 - lia3))
        seriesData.add(CustomDataEntry(dates[3].first, asset4, lia4, asset4 - lia4))
        seriesData.add(CustomDataEntry(dates[4].first, asset5, lia5, asset5 - lia5))

        val set = Set.instantiate()
        set.data(seriesData)
        val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

        assetLine = cartesian.line(series1Mapping)
        assetLine.name("Assets")
        assetLine.hovered().markers().enabled(true)
        assetLine.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        assetLine.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        liabilityLine = cartesian.line(series2Mapping)
        liabilityLine.name("Liabilities")
        liabilityLine.hovered().markers().enabled(true)
        liabilityLine.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        liabilityLine.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        netWorthLine = cartesian.line(series3Mapping)
        netWorthLine.name("Net-worth")
        netWorthLine.hovered().markers().enabled(true)
        netWorthLine.hovered().markers()
            .type(MarkerType.CIRCLE)
            .size(4.0)
        netWorthLine.tooltip()
            .position("right")
            .anchor(Anchor.LEFT_CENTER)
            .offsetX(5.0)
            .offsetY(5.0)

        cartesian.legend().enabled(true)
        cartesian.legend().fontSize(13.0)
        cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)
        chart.setChart(cartesian)


 }

    private fun updateAssetLines(){
        val seriesData: MutableList<DataEntry> = ArrayList()
        val asset1 = assetYear1.text.toString().toLongNumber()
        val asset2 = assetYear2.text.toString().toLongNumber()
        val asset3 = assetYear3.text.toString().toLongNumber()
        val asset4 = assetYear4.text.toString().toLongNumber()
        val asset5 = assetYear5.text.toString().toLongNumber()
        val lia1 = liaYear1.text.toString().toLongNumber()
        val lia2 = liaYear2.text.toString().toLongNumber()
        val lia3 = liaYear3.text.toString().toLongNumber()
        val lia4 = liaYear4.text.toString().toLongNumber()
        val lia5 = liaYear5.text.toString().toLongNumber()
        seriesData.add(CustomDataEntry(dates[4].first, asset5, lia5, asset5 - lia5))
        seriesData.add(CustomDataEntry(dates[3].first, asset4, lia4, asset4 - lia4))
        seriesData.add(CustomDataEntry(dates[2].first, asset3, lia3, asset3 - lia3))
        seriesData.add(CustomDataEntry(dates[1].first, asset2, lia2, asset2 - lia2))
        seriesData.add(CustomDataEntry(dates[0].first,  asset1, lia1, asset1 - lia1))

        val set = Set.instantiate()
        set.data(seriesData)
        val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
        val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
        val series3Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")
        assetLine.data(series1Mapping)
        liabilityLine.data(series2Mapping)
        netWorthLine.data(series3Mapping)
    }

    private class CustomDataEntry internal constructor(
        x: String?,
        value: Number?,
        value2: Number?,
        value3: Number?
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }
}