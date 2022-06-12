package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.ListEntityMapper.mapToEntity
import com.example.stocktick.ui.mutual_funds.asset_recorder.ListEntityMapper.mapToList
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.EditDetailsModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.LabelValue
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.FixedDeposit
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This view model will have all the dynamic data required in asset recorder section*/
private const val TAG = "FdViewModel"

class AssetDataViewModel(application: Application) : AndroidViewModel(application) {

    private val _fdList = MutableStateFlow(mutableListOf<FixedDeposit>())

    private val _cardDataList = MutableStateFlow(mutableListOf<LabelValue>())
    val cardDataList = _cardDataList

    fun deleteElement(category: Int, position: Int, result: (isSuccessful: Boolean) -> Unit) {
        when (category) {
            AppData.FIXED_DEPOSIT -> {
                //update the api
                // if failed, return with false response
                // else change fdList
                try {
                    _fdList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
        }

    }

    fun addElement(list : List<EditDetailsModel>, category: Int, result: (isSuccessful: Boolean) -> Unit) {
        try {
            when (category) {
                AppData.FIXED_DEPOSIT -> {
                    //update the api
                    // if failed, return with false response
                    // else change fdList
                    val fd = try {
                        list.mapToEntity(category) as FixedDeposit
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        FixedDeposit()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        FixedDeposit()
                    }
                    //call post api
                    _fdList.value.add(fd)
                    result(true)
                }
            }
        } catch (e: IndexOutOfBoundsException) {

        } catch (e: ClassCastException) {

        }
    }



    fun setElement(position: Int, list : List<EditDetailsModel>, category: Int, result: (isSuccessful: Boolean) -> Unit) {
        try {
            when (category) {
                AppData.FIXED_DEPOSIT -> {
                    //update the api
                    // if failed, return with false response
                    // else change fdList
                    val fd = try {
                        list.mapToEntity(category) as FixedDeposit
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "setElement: Index out of bounds", )
                        FixedDeposit()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "setElement: Class Cast Exception", )
                        FixedDeposit()
                    }
                    //call post api
                    _fdList.value[position] = fd
                    result(true)
                }
            }
        } catch (e: IndexOutOfBoundsException) {

        } catch (e: ClassCastException) {

        }
    }


    fun populateCardDataList(category: Int) {
        when (category) {
            AppData.FIXED_DEPOSIT -> {
                if (_fdList.value.isEmpty()) {
                    _cardDataList.value.clear() //The reason I am clearing is to avoid displaying false data in the time it loads new data
                    refreshFixedDeposits()
                } else {
                    _cardDataList.value = _fdList.value.map {
                        LabelValue(
                            title = it.amount_invested, // I expect to get it in correct format i.e. having unit INR of needed
                            particular1 = it.bank_name,
                            particular2 = it.maturity_date
                        )
                    }.toMutableList()
                }
            }
        }
    }


    private fun refreshFixedDeposits() {
        _fdList.value = mutableListOf(
            FixedDeposit(
                amount_invested = "1235000",
                bank_name = "State Bank of India",
                maturity_date = "12 / 02 / 2003"
            )
        )
        //after populating
        _cardDataList.value = _fdList.value.map {
            LabelValue(
                title = it.amount_invested, // I expect to get it in correct format i.e. having unit INR of needed
                particular1 = it.bank_name,
                particular2 = it.maturity_date
            )
        }.toMutableList()
    }

    fun getDataDetailsList(category: Int, position: Int): List<EditDetailsModel> =
        when (category){
            AppData.FIXED_DEPOSIT -> {
                val list = try {
                    if (position == -1) FixedDeposit().mapToList(category) else _fdList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            else -> {
                listOf()
            }
        }


}