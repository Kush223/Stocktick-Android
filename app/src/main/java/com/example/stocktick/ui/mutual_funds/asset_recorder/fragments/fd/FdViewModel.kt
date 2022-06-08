package com.example.stocktick.ui.mutual_funds.asset_recorder.fragments.fd

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.FixedDeposit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val TAG = "FdViewModel"
class FdViewModel(application: Application) : AndroidViewModel(application) {

    private val _fdList = MutableStateFlow(mutableListOf<FixedDeposit>())

    val fdList: StateFlow<List<FixedDeposit>> = _fdList

    fun deleteElement(position: Int){
        try {
            _fdList.value.removeAt(position)
        } catch (e : IndexOutOfBoundsException){
            Log.e(TAG, "Delete element : Index out of bounds", )
        }
    }

    @Throws(IndexOutOfBoundsException::class)
    fun getElement(position: Int): FixedDeposit = _fdList.value[position]


    fun setElement(position: Int, model : FixedDeposit) {
        try {
            _fdList.value[position] = model
        } catch (e : IndexOutOfBoundsException){

        }
    }

    fun addElement(model : FixedDeposit){
        try {
            _fdList.value.add(model)
        } catch (e : IndexOutOfBoundsException){

        }
    }


    init {

        _fdList.value = mutableListOf(
            FixedDeposit(
                amount_invested = "123,000",
                maturity_date = "12 Feb, 1232",
                bank_name = "State Bank of India"
            ),
            FixedDeposit(
                amount_invested = "153,00",
                maturity_date = "12 Feb, 1232",
                bank_name = "Central Bank of India"
            ),
            FixedDeposit(
                amount_invested = "123,145",
                maturity_date = "12 Feb, 1232",
                bank_name = "Union Bank of India"
            ),
            FixedDeposit(
                amount_invested = "523,000",
                maturity_date = "12 Jan, 1232",
                bank_name = "HDFC bank"
            )
            )
    }
}