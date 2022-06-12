package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.bank_account

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.BaModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "BaViewModel"
class BaViewModel  (application: Application) : AndroidViewModel(application) {

    private val _modList = MutableStateFlow(mutableListOf<BaModel>())

    val modList: StateFlow<List<BaModel>> = _modList

    fun deleteElement(position: Int){
        try {
            _modList.value.removeAt(position)
        } catch (e : IndexOutOfBoundsException){
            Log.e(TAG, "Delete element : Index out of bounds", )
        }
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getElement(position: Int): BaModel = _modList.value[position]


    fun setElement(position: Int, model : BaModel) {
        try {
            _modList.value[position] = model
        } catch (e : IndexOutOfBoundsException){

        }
    }

    fun addElement(model : BaModel){
        try {
            _modList.value.add(model)
        } catch (e : IndexOutOfBoundsException){

        }
    }


    init {

        _modList.value = mutableListOf(
            //Fill it
        BaModel(bank_name = "Bank of Baroda", account_type = "Saving Account", last_4_digit_of_account_number = "1235"),
        BaModel(bank_name = "Bank of Baroda", account_type = "Saving Account", last_4_digit_of_account_number = "1235"),
        BaModel(bank_name = "Bank of Baroda", account_type = "Saving Account", last_4_digit_of_account_number = "1235"),
        )

    }
}