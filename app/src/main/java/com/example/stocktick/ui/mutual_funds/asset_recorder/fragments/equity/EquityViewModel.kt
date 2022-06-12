package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.equity

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.EquityModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "EquityViewModel"
class EquityViewModel  (application: Application) : AndroidViewModel(application) {

    private val _modList = MutableStateFlow(mutableListOf<EquityModel>())

    val modList: StateFlow<List<EquityModel>> = _modList

    fun deleteElement(position: Int){
        try {
            _modList.value.removeAt(position)
        } catch (e : IndexOutOfBoundsException){
            Log.e(TAG, "Delete element : Index out of bounds", )
        }
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getElement(position: Int): EquityModel = _modList.value[position]


    fun setElement(position: Int, model : EquityModel) {
        try {
            _modList.value[position] = model
        } catch (e : IndexOutOfBoundsException){

        }
    }

    fun addElement(model : EquityModel){
        try {
            _modList.value.add(model)
        } catch (e : IndexOutOfBoundsException){

        }
    }


    init {
//
//        _modList.value = mutableListOf(
//            //Fill it
//        )
    }
}