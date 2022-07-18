package com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds.models.ExploreMoreMfCategoryModel
import com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds.models.ExploreMoreMfModel
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "ExploreMoreViewModel"
class ExploreMoreViewModel(application: Application) : AndroidViewModel(application) {
    private var tokenSharedPreference: String
    val categories = MutableStateFlow(mutableListOf<ExploreMoreMfCategoryModel>())

    init {
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()
        updateMfCategories()
    }

    private fun updateMfCategories(){

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getExploreMoreCategories(
                    authToken = tokenSharedPreference
                )
                if (response.isSuccessful && response.body() != null && response.body()!!
                        .isNotEmpty()
                ){
                    Log.d(TAG, "updateMfCategories: ${response.body()}")
                    val mfCatList = response.body() ?: return@launch
                    withContext(Dispatchers.Main){
                       categories.value = mfCatList.toMutableList()
                    }
                }
            } catch (e: Exception){
                Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
            }
        }
    }


    fun getMfList(id : Int, result : (list : List<ExploreMoreMfModel>) -> Unit){

        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getExploreMoreMutualFund(
                    authToken = tokenSharedPreference,
                    catgId = id
                )
                if (response.isSuccessful && response.body() != null && response.body()!!
                        .isNotEmpty()
                ){
                    Log.d(TAG, "updateMfCategories: ${response.body()}")
                    val mfCatList = response.body() ?: return@launch
                    withContext(Dispatchers.Main){
                        result(
                            mfCatList
                        )
                    }
                }
            } catch (e: Exception){
                Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
            }
        }
    }


//    init {
//        viewModelScope.launch {
//            delay(500)
//            categories.value = mutableListOf(
//                ExploreMoreMfCategoryModel(fundCatgName = "High Return"),
//                ExploreMoreMfCategoryModel(fundCatgName = "SIP with $500"),
//                ExploreMoreMfCategoryModel(fundCatgName = "Tax Saving"),
//                ExploreMoreMfCategoryModel(fundCatgName = "Large Cap"),
//                ExploreMoreMfCategoryModel(fundCatgName = "Mid Cap"),
//                ExploreMoreMfCategoryModel(fundCatgName = "Small Cap"),
//            )
//        }
//    }

}