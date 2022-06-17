package com.example.stocktick.ui.mutual_funds

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.domain_models.MfModel
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.network_models.ClickCountModel
import com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.network_models.GetCategories
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "MainViewModelMF"
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var tokenSharedPreference: String


    val mfCategoriesTitle : MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }


    val mfCategories : MutableLiveData<List<GetCategories>> by lazy {
        MutableLiveData<List<GetCategories>>()
    }

    val mfList: MutableLiveData<List<MfModel>> by lazy {
        MutableLiveData<List<MfModel>>()
    }


    init {
        Log.d(TAG, "init is called: ")
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
        updateMfCategories()

    }


    private fun updateMfCategories()
    {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getCategories(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful && response.body() != null && response.body()!!
                        .isNotEmpty()
                ){
                    Log.d(TAG, "updateMfCategories: ${response.body()}")
                    val mfCatList = response.body() ?: return@launch
                    withContext(Dispatchers.Main){

                        mfCategories.value = mfCatList

                        mfCategoriesTitle.value = mfCatList.map {
                            if (it.lock_status == "LOCKED")
                            "Locked"
                            else it.catg_name ?: "Locked"

                        }.filter {
                            (it != "Locked")
                        }
                    }
                }
            } catch (e: Exception){
                Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
            }
        }
    }

    fun updateMfList(
        cat: Int
    ){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getMfList(
                    authToken = tokenSharedPreference,
                        catg_id = cat
                )
                if (response.isSuccessful && response.body() != null && response.body()!!
                        .isNotEmpty()
                ){
                    Log.d(TAG, "updateMfCategories: ${response.body()}")
                    val localMfList = response.body() ?: return@launch
                    withContext(Dispatchers.Main){

                         mfList.value = localMfList.map {
                             MfModel(
                                 mfName = it.fundName ?: "Fund name not found",
                                 iconUrl = it.icon ?: "",
                                 oneDayR = it.oneday.toString(),
                                 oneYearR = it.oneyear.toString(),
                                 threeYearR = it.threeyear.toString(),
                                 shortDescription = it.longDesc ?: "",
                                 redirectUrl = it.fetchUrl ?: "",
                                 lockStatus = it.lockStatus ?: "UNLOCKED",
                                 fundId = it.id ?: 1
                             )
                         }
                    }
                }
            } catch (e: Exception){
                Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
            }
        }
    }
    fun clickCount(
        catgId: Int,
        fund_id : Int
    ){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.mfClickCount(
                    authToken = tokenSharedPreference,
                    ClickCountModel(
                        catgId.toString(),
                        fund_id.toString()
                    )
                )
                if (response.isSuccessful && response.body() != null
                ){
                    Log.d(TAG, "clickCount: Item added successfully :${response.body()}")
                }
            } catch (e: Exception){
                Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
            }
        }
    }






}