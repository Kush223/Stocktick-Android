package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.aif

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.AifModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "AifViewModel"
class AifViewModel (application: Application) : AndroidViewModel(application) {

    private val _modList = MutableStateFlow(mutableListOf<AifModel>())

    val modList: StateFlow<List<AifModel>> = _modList

    fun deleteElement(position: Int){
        try {
            _modList.value.removeAt(position)
        } catch (e : IndexOutOfBoundsException){
            Log.e(TAG, "Delete element : Index out of bounds", )
        }
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getElement(position: Int): AifModel = _modList.value[position]


    fun setElement(position: Int, model : AifModel) {
        try {
            _modList.value[position] = model
        } catch (e : IndexOutOfBoundsException){

        }
    }

    fun addElement(model : AifModel){
        try {
            _modList.value.add(model)
        } catch (e : IndexOutOfBoundsException){

        }
    }


    init {

        _modList.value = mutableListOf(
            AifModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            ),
            AifModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            ),
            AifModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            ),
            AifModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            )
        )
    }
}