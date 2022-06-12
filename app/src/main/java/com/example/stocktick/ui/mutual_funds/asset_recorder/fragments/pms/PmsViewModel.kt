package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.pms

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.PmsModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "PmsViewModel"
class PmsViewModel  (application: Application) : AndroidViewModel(application) {

    private val _modList = MutableStateFlow(mutableListOf<PmsModel>())

    val modList: StateFlow<List<PmsModel>> = _modList

    fun deleteElement(position: Int){
        try {
            _modList.value.removeAt(position)
        } catch (e : IndexOutOfBoundsException){
            Log.e(TAG, "Delete element : Index out of bounds", )
        }
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getElement(position: Int): PmsModel = _modList.value[position]


    fun setElement(position: Int, model : PmsModel) {
        try {
            _modList.value[position] = model
        } catch (e : IndexOutOfBoundsException){

        }
    }

    fun addElement(model : PmsModel){
        try {
            _modList.value.add(model)
        } catch (e : IndexOutOfBoundsException){

        }
    }


    init {

        _modList.value = mutableListOf(
            PmsModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            ),
            PmsModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            ),
            PmsModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            ),
            PmsModel(invested_value = "152000 INR",
                amc_name = "Baroda",
                pms_aif_name = "Hi"
            )
        )
    }
}