package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stocktick.network.RetrofitClientInstance
import com.example.stocktick.ui.mutual_funds.asset_recorder.ListEntityMapper.mapToEntity
import com.example.stocktick.ui.mutual_funds.asset_recorder.ListEntityMapper.mapToList
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.EditDetailsModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.LabelValue
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.*
import com.example.stocktick.utility.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * This view model will have all the dynamic data required in asset recorder section*/
private const val TAG = "FdViewModel"

class AssetDataViewModel(application: Application) : AndroidViewModel(application) {

    private var tokenSharedPreference: String

    init {
        Log.d(TAG, "init is called: ")
        val sharedPreferences: SharedPreferences =
            application.getSharedPreferences(Constant.USER, Activity.MODE_PRIVATE)
        tokenSharedPreference =
            sharedPreferences.getString(Constant.TOKEN, Constant.SHAREDPREFERENCES_TOKEN_A).toString()


    }


    private val _fdList = MutableStateFlow(mutableListOf<FixedDeposit>())
    private val  bankList = MutableStateFlow(mutableListOf<BankModel>())
    private val  mutualList = MutableStateFlow(mutableListOf<MutualModel>())
    private val  lockerList = MutableStateFlow(mutableListOf<LockerModel>())
    private val  indianEquityList = MutableStateFlow(mutableListOf<IndianEquityModel>())
    private val  usEquityList = MutableStateFlow(mutableListOf<UsEquityModel>())
    private val  cryptoList = MutableStateFlow(mutableListOf<CryptoModel>())
    private val  bondsList = MutableStateFlow(mutableListOf<BondsModel>())
    private val  offMarketList = MutableStateFlow(mutableListOf<OffMarketModel>())
    private val  aifList = MutableStateFlow(mutableListOf<AifModel>())
    private val  pmsList = MutableStateFlow(mutableListOf<PmsModel>())
    private val  epfList = MutableStateFlow(mutableListOf<EpfModel>())
    private val  ppfList = MutableStateFlow(mutableListOf<PpfModel>())
    private val  realEstateList = MutableStateFlow(mutableListOf<RealEstateModel>())
    private val  npsTierList = MutableStateFlow(mutableListOf<NpsTierModel>())
    private val  othersList = MutableStateFlow(mutableListOf<OthersModel>())
    private val  lifeInsuranceList = MutableStateFlow(mutableListOf<LifeInsuranceModel>())
    private val  healthInsuranceList = MutableStateFlow(mutableListOf<HealthInsuranceModel>())
    private val  motorInsuranceList = MutableStateFlow(mutableListOf<MotorInsuranceModel>())
    private val  loanList = MutableStateFlow(mutableListOf<LoanModel>())

    private val _cardDataList = MutableStateFlow(mutableListOf<LabelValue>())
    val cardDataList = _cardDataList


    fun deleteElement(category: Int, position: Int, result: (isSuccessful: Boolean) -> Unit) {
        when (category) {
            AppData.FIXED_DEPOSIT -> {
                //update the api
                // if failed, return with false response
                // else change fdList
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            _fdList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                _fdList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.BANK_ACCOUNT -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            bankList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                bankList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.MUTUAL_FUND -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            mutualList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                mutualList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.LOCKER -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            lockerList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                lockerList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.INDIAN_EQUITY -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            indianEquityList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                indianEquityList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.US_EQUITY -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            usEquityList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                usEquityList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.CRYPTO -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            cryptoList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                cryptoList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.BONDS -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            bondsList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                bondsList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.OFF_MARKET -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            offMarketList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                offMarketList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.AIF -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            aifList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                aifList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.PMS -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            pmsList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                pmsList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.EPF -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            epfList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                epfList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.PPF -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            ppfList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                ppfList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.REAL_ESTATE -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            realEstateList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                realEstateList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.NPS_TIER_1 -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            npsTierList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                npsTierList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.OTHERS -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            othersList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                othersList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.LIFE_INSURANCE -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            lifeInsuranceList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                lifeInsuranceList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.HEALTH_INSURANCE -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            healthInsuranceList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                healthInsuranceList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.MOTOR_INSURANCE -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            motorInsuranceList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                motorInsuranceList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }
            AppData.LOAN -> {
                viewModelScope.launch(Dispatchers.IO){
                    try {
                        val response = RetrofitClientInstance.retrofitService.deleteData(
                            authToken = tokenSharedPreference,
                            loanList.value[position].id
                        )
                        if (response.isSuccessful){
                            withContext(Dispatchers.Main){
                                loanList.value.removeAt(position)
                                result(true)
                            }
                        }
                        else {
                            withContext(Dispatchers.Main){result(false) }
                        }
                    } catch (e : Exception){
                        Log.e(TAG, "deleteElement: Error :${e.localizedMessage}", )
                        withContext(Dispatchers.Main){result(false) }
                    }

                }
            }

        }

    }

    fun addElement(list : List<EditDetailsModel>, category: Int, result: (isSuccessful: Boolean) -> Unit) {
        try {
            when (category) {
                AppData.FIXED_DEPOSIT -> {
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
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "fd",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to fd.bank_name,
                                    "branch_address" to fd.branch_addr,
                                    "deposit_name" to fd.deposit_holders_name,
                                    "amount_invested" to fd.amount_invested,
                                    "investment_date" to fd.investment_date,
                                    "investment_duration" to fd.investment_duration,
                                    "rate_interest" to fd.rate_of_interest,
                                    "maturity_date" to fd.maturity_date,
                                    "maturity_amount" to fd.maturity_amount,
                                    "nominee1" to fd.nominee_name,
                                    "relationship1" to fd.relationship,
                                    "allocation1" to fd.allocation,
                                    "nominee2" to fd.nominee_name2,
                                    "relationship2" to fd.relationship2,
                                    "allocation2" to fd.allocation2
                                )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){
                                    //call post api
                                    _fdList.value.add(fd)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }


                }

                AppData.BANK_ACCOUNT -> {
                    val mod = try {
                        list.mapToEntity(category) as BankModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        BankModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        BankModel()
                    }

                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "bank_account",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "branch_address" to mod.branch_address,
                                    "account_holder" to mod.account_holder_name,
                                    "last_digit_account" to mod.last_4_digit_of_account_number,
                                    "account_type" to mod.account_type,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){
                                    //call post api
                                    bankList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }

                }
                AppData.MUTUAL_FUND -> {
                    val mod = try {
                        list.mapToEntity(category) as MutualModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        MutualModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        MutualModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "mf",
                                editId = "",
                                body = mapOf(
                                    "scheme_name" to mod.scheme_name,
                                    "purchase_amount" to mod.purchase_amount,
                                    " puchase_nav" to mod.purchase_nav,
                                    "current_nav" to mod.current_nav,
                                    "current_amount" to mod.current_amount,
                                    "div_amount" to mod.div_amount,
                                    "gain_short" to mod.gain_short_term,
                                    "gain_long" to mod.gain_long_term,
                                    "return_abs" to mod.return_absolute,
                                    "return_cagr" to mod.return_cagr,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    mutualList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.LOCKER -> {
                    val mod = try {
                        list.mapToEntity(category) as LockerModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        LockerModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        LockerModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "locker",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "bank_address" to mod.bank_address,
                                    "locker_number" to mod.locker_number,
                                    "locker_name" to mod.locker_holder_name,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){
                                    //call post api
                                    //call post api
                                    lockerList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.INDIAN_EQUITY -> {
                    val mod = try {
                        list.mapToEntity(category) as IndianEquityModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        IndianEquityModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        IndianEquityModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "indian_equity",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    indianEquityList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.US_EQUITY -> {
                    val mod = try {
                        list.mapToEntity(category) as UsEquityModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        UsEquityModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        UsEquityModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "us_equity",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    usEquityList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.CRYPTO -> {
                    val mod = try {
                        list.mapToEntity(category) as CryptoModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        CryptoModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        CryptoModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "crypto",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    cryptoList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.BONDS -> {
                    val mod = try {
                        list.mapToEntity(category) as BondsModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        BondsModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        BondsModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "bonds",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    bondsList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.OFF_MARKET -> {
                    val mod = try {
                        list.mapToEntity(category) as OffMarketModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        OffMarketModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        OffMarketModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "off_market",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    offMarketList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.AIF -> {
                    val mod = try {
                        list.mapToEntity(category) as AifModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        AifModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        AifModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "aif",
                                editId = "",
                                body = mapOf(
                                    "amc_name" to mod.amc_name,
                                    "pms_name" to mod.pms_aif_name,
                                    "interested_value" to mod.invested_value,
                                    "current_amount" to mod.current_amount,
                                    "account_number" to mod.account_number,
                                    "invested_date" to mod.invested_date,
                                    "return_abs" to mod.return_absolute,
                                    "return_cagr" to mod.return_cagr,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    aifList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.PMS -> {
                    val mod = try {
                        list.mapToEntity(category) as PmsModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        PmsModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        PmsModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "pms",
                                editId = "",
                                body = mapOf(
                                    "amc_name" to mod.amc_name,
                                    "pms_name" to mod.pms_aif_name,
                                    "interested_value" to mod.invested_value,
                                    "current_amount" to mod.current_amount,
                                    "account_number" to mod.account_number,
                                    "invested_date" to mod.invested_date,
                                    "return_abs" to mod.return_absolute,
                                    "return_cagr" to mod.return_cagr,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    pmsList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.EPF -> {
                    val mod = try {
                        list.mapToEntity(category) as EpfModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        EpfModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        EpfModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "epf",
                                editId = "",
                                body = mapOf(
                                    "epf_number" to mod.epf_no,
                                    "balance" to mod.balance,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    epfList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.PPF -> {
                    val mod = try {
                        list.mapToEntity(category) as PpfModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        PpfModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        PpfModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "ppf",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "branch_address" to mod.branch_address,
                                    "ppf_account_number" to mod.ppf_account_no,
                                    "balance" to mod.balance,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    ppfList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.REAL_ESTATE -> {
                    val mod = try {
                        list.mapToEntity(category) as RealEstateModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        RealEstateModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        RealEstateModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "real_estate",
                                editId = "",
                                body = mapOf(
                                    "property_name" to mod.property_name,
                                    "invested_value" to mod.invested_value,
                                    "invested_date" to mod.investment_date,
                                    "current_value" to mod.current_value,
                                    "property_type" to mod.property_type,
                                    "property_size" to mod.property_size,
                                    "location_pincode" to mod.location_pincode,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    realEstateList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.NPS_TIER_1 -> {
                    val mod = try {
                        list.mapToEntity(category) as NpsTierModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        NpsTierModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        NpsTierModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "nps_tier1",
                                editId = "",
                                body = mapOf(
                                    "pran" to mod.pran,
                                    "current_balance" to mod._current_balance,
                                    "account_type" to mod.type_of_account_,
                                    "nominee_details" to mod.nominee_details,
                                    "bank_details" to mod.bank_details,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    npsTierList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.OTHERS -> {
                    val mod = try {
                        list.mapToEntity(category) as OthersModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        OthersModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        OthersModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "fd",
                                editId = "others",
                                body = mapOf(
                                    "instrument_name" to mod.instrument_name,
                                    "purchase_value" to mod.purchase_value,
                                    "current_value" to mod.current_current,
                                    "purchase_date" to mod.purchase_date,
                                    "units" to mod.no_of_units,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    othersList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.LIFE_INSURANCE -> {
                    val mod = try {
                        list.mapToEntity(category) as LifeInsuranceModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        LifeInsuranceModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        LifeInsuranceModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "life_insurance",
                                editId = "",
                                body = mapOf(
                                    "plan_name" to mod.plan_name,
                                    "policy_number" to mod.policy_number,
                                    "policy_holder_name" to mod.policy_holders_name,
                                    "sum_assured" to mod.sum_assured_,
                                    "policy_start_date" to mod.policy_start_date,
                                    "policy_maturity_date" to mod.policy_maturity_date,
                                    "policy_term" to mod.policy_term_years,
                                    "premium_payment" to mod.premium_payment_term_years,
                                    "premium_amount" to mod.premium_amount,
                                    "premium_payment_frequency" to mod.premium_payment_frequency_,
                                    "renewal_date" to mod.renewal_date,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    lifeInsuranceList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.HEALTH_INSURANCE -> {
                    val mod = try {
                        list.mapToEntity(category) as HealthInsuranceModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        HealthInsuranceModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        HealthInsuranceModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "health_insurance",
                                editId = "",
                                body = mapOf(
                                    "plan_name" to mod.plan_name,
                                    "policy_number" to mod.policy_number,
                                    "policy_holder_name" to mod.policy_holders_name,
                                    "family_member _covered" to mod.family_member_covered,
                                    "sum_assured" to mod.sum_assured_,
                                    "policy_start_date" to mod.policy_start_date,
                                    "renewal_date" to mod.renewal_date,
                                    "premium_amount" to mod.premium_amount,
                                    "tpa_contact_number" to mod.tpa_contact_number,
                                    "company_number" to mod.company_help_desk_number,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    healthInsuranceList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.MOTOR_INSURANCE -> {
                    val mod = try {
                        list.mapToEntity(category) as MotorInsuranceModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        MotorInsuranceModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        MotorInsuranceModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "motor_insurance",
                                editId = "",
                                body = mapOf(
                                    "plan_name" to mod.plan_name,
                                    "policy_number" to mod.policy_number,
                                    "insured_declared_value" to mod.insured_declared_value,
                                    "type_start_cover" to mod.type_of_cover,
                                    "policy_holder_name" to mod.policy_holders_name,
                                    "policy_start_date" to mod.policy_start_date,
                                    "premium_amount" to mod.premium_amount,
                                    "renewal_date" to mod.renewal_date,
                                    "company_helpdesk_number" to mod.company_helpdesk_number,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    motorInsuranceList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.LOAN -> {
                    val mod = try {
                        list.mapToEntity(category) as LoanModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        LoanModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        LoanModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "loan",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "loan_account_number" to mod.loan_account_number,
                                    "loan_amount" to mod.loan_amount,
                                    "asset_name" to mod.asset_name,
                                    "asset_type" to mod.asset_type,
                                    "loan_start_date" to mod.loan_start_date,
                                    "loan_tenure" to mod.loan_tenure,
                                    "emi_amount" to mod.emi_amount,
                                    "roi" to mod.roi,
                                    "ecs_bank" to mod.ecs_bank,
                                    "insurance_protect_loan" to mod.insurance_protect_loan,
                                    "current_outstanding" to mod.current_outstanding,
                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    loanList.value.add(mod)
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }


            }
        } catch (e: IndexOutOfBoundsException) {
            result(false)
        } catch (e: ClassCastException) {
            result(false)
        }
    }

    fun setElement(position: Int,list : List<EditDetailsModel>, category: Int, result: (isSuccessful: Boolean) -> Unit) {
        try {
            when (category) {
                AppData.FIXED_DEPOSIT -> {
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
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "fd",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to fd.bank_name,
                                    "branch_address" to fd.branch_addr,
                                    "deposit_name" to fd.deposit_holders_name,
                                    "amount_invested" to fd.amount_invested,
                                    "investment_date" to fd.investment_date,
                                    "investment_duration" to fd.investment_duration,
                                    "rate_interest" to fd.rate_of_interest,
                                    "maturity_date" to fd.maturity_date,
                                    "maturity_amount" to fd.maturity_amount,
                                    "nominee1" to fd.nominee_name,
                                    "relationship1" to fd.relationship,
                                    "allocation1" to fd.allocation,
                                    "nominee2" to fd.nominee_name2,
                                    "relationship2" to fd.relationship2,
                                    "allocation2" to fd.allocation2
                                )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){
                                    //call post api
                                    _fdList.value[position] = fd
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }


                }

                AppData.BANK_ACCOUNT -> {
                    val mod = try {
                        list.mapToEntity(category) as BankModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        BankModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        BankModel()
                    }

                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "bank_account",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "branch_address" to mod.branch_address,
                                    "account_holder" to mod.account_holder_name,
                                    "last_digit_account" to mod.last_4_digit_of_account_number,
                                    "account_type" to mod.account_type,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){
                                    //call post api
                                    bankList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }

                }
                AppData.MUTUAL_FUND -> {
                    val mod = try {
                        list.mapToEntity(category) as MutualModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        MutualModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        MutualModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "mf",
                                editId = "",
                                body = mapOf(
                                    "scheme_name" to mod.scheme_name,
                                    "purchase_amount" to mod.purchase_amount,
                                    " puchase_nav" to mod.purchase_nav,
                                    "current_nav" to mod.current_nav,
                                    "current_amount" to mod.current_amount,
                                    "div_amount" to mod.div_amount,
                                    "gain_short" to mod.gain_short_term,
                                    "gain_long" to mod.gain_long_term,
                                    "return_abs" to mod.return_absolute,
                                    "return_cagr" to mod.return_cagr,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    mutualList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.LOCKER -> {
                    val mod = try {
                        list.mapToEntity(category) as LockerModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        LockerModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        LockerModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "locker",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "bank_address" to mod.bank_address,
                                    "locker_number" to mod.locker_number,
                                    "locker_name" to mod.locker_holder_name,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){
                                    //call post api
                                    //call post api
                                    lockerList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.INDIAN_EQUITY -> {
                    val mod = try {
                        list.mapToEntity(category) as IndianEquityModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        IndianEquityModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        IndianEquityModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "indian_equity",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    indianEquityList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.US_EQUITY -> {
                    val mod = try {
                        list.mapToEntity(category) as UsEquityModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        UsEquityModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        UsEquityModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "us_equity",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    usEquityList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.CRYPTO -> {
                    val mod = try {
                        list.mapToEntity(category) as CryptoModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        CryptoModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        CryptoModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "crypto",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    cryptoList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.BONDS -> {
                    val mod = try {
                        list.mapToEntity(category) as BondsModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        BondsModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        BondsModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "bonds",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    bondsList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.OFF_MARKET -> {
                    val mod = try {
                        list.mapToEntity(category) as OffMarketModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        OffMarketModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        OffMarketModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "off_market",
                                editId = "",
                                body = mapOf(
                                    "broker_name" to mod.broker_name,
                                    "account_number" to mod.account_number,
                                    "instrument_name" to mod.instrument_name,
                                    "qty" to mod.qty,
                                    "purchase_price" to mod.purchase_price,
                                    "purchase_value" to mod.purchase_value,
                                    "market_price" to mod.market_price,
                                    "market_value" to mod.market_value,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    offMarketList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.AIF -> {
                    val mod = try {
                        list.mapToEntity(category) as AifModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        AifModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        AifModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "aif",
                                editId = "",
                                body = mapOf(
                                    "amc_name" to mod.amc_name,
                                    "pms_name" to mod.pms_aif_name,
                                    "interested_value" to mod.invested_value,
                                    "current_amount" to mod.current_amount,
                                    "account_number" to mod.account_number,
                                    "invested_date" to mod.invested_date,
                                    "return_abs" to mod.return_absolute,
                                    "return_cagr" to mod.return_cagr,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    aifList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.PMS -> {
                    val mod = try {
                        list.mapToEntity(category) as PmsModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        PmsModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        PmsModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "pms",
                                editId = "",
                                body = mapOf(
                                    "amc_name" to mod.amc_name,
                                    "pms_name" to mod.pms_aif_name,
                                    "interested_value" to mod.invested_value,
                                    "current_amount" to mod.current_amount,
                                    "account_number" to mod.account_number,
                                    "invested_date" to mod.invested_date,
                                    "return_abs" to mod.return_absolute,
                                    "return_cagr" to mod.return_cagr,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    pmsList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.EPF -> {
                    val mod = try {
                        list.mapToEntity(category) as EpfModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        EpfModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        EpfModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "epf",
                                editId = "",
                                body = mapOf(
                                    "epf_number" to mod.epf_no,
                                    "balance" to mod.balance,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    epfList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.PPF -> {
                    val mod = try {
                        list.mapToEntity(category) as PpfModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        PpfModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        PpfModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "ppf",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "branch_address" to mod.branch_address,
                                    "ppf_account_number" to mod.ppf_account_no,
                                    "balance" to mod.balance,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    ppfList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.REAL_ESTATE -> {
                    val mod = try {
                        list.mapToEntity(category) as RealEstateModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        RealEstateModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        RealEstateModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "real_estate",
                                editId = "",
                                body = mapOf(
                                    "property_name" to mod.property_name,
                                    "invested_value" to mod.invested_value,
                                    "invested_date" to mod.investment_date,
                                    "current_value" to mod.current_value,
                                    "property_type" to mod.property_type,
                                    "property_size" to mod.property_size,
                                    "location_pincode" to mod.location_pincode,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    realEstateList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.NPS_TIER_1 -> {
                    val mod = try {
                        list.mapToEntity(category) as NpsTierModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        NpsTierModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        NpsTierModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "nps_tier1",
                                editId = "",
                                body = mapOf(
                                    "pran" to mod.pran,
                                    "current_balance" to mod._current_balance,
                                    "account_type" to mod.type_of_account_,
                                    "nominee_details" to mod.nominee_details,
                                    "bank_details" to mod.bank_details,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    npsTierList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.OTHERS -> {
                    val mod = try {
                        list.mapToEntity(category) as OthersModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        OthersModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        OthersModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "fd",
                                editId = "others",
                                body = mapOf(
                                    "instrument_name" to mod.instrument_name,
                                    "purchase_value" to mod.purchase_value,
                                    "current_value" to mod.current_current,
                                    "purchase_date" to mod.purchase_date,
                                    "units" to mod.no_of_units,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    othersList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.LIFE_INSURANCE -> {
                    val mod = try {
                        list.mapToEntity(category) as LifeInsuranceModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        LifeInsuranceModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        LifeInsuranceModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "life_insurance",
                                editId = "",
                                body = mapOf(
                                    "plan_name" to mod.plan_name,
                                    "policy_number" to mod.policy_number,
                                    "policy_holder_name" to mod.policy_holders_name,
                                    "sum_assured" to mod.sum_assured_,
                                    "policy_start_date" to mod.policy_start_date,
                                    "policy_maturity_date" to mod.policy_maturity_date,
                                    "policy_term" to mod.policy_term_years,
                                    "premium_payment" to mod.premium_payment_term_years,
                                    "premium_amount" to mod.premium_amount,
                                    "premium_payment_frequency" to mod.premium_payment_frequency_,
                                    "renewal_date" to mod.renewal_date,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    lifeInsuranceList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.HEALTH_INSURANCE -> {
                    val mod = try {
                        list.mapToEntity(category) as HealthInsuranceModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        HealthInsuranceModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        HealthInsuranceModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "health_insurance",
                                editId = "",
                                body = mapOf(
                                    "plan_name" to mod.plan_name,
                                    "policy_number" to mod.policy_number,
                                    "policy_holder_name" to mod.policy_holders_name,
                                    "family_member _covered" to mod.family_member_covered,
                                    "sum_assured" to mod.sum_assured_,
                                    "policy_start_date" to mod.policy_start_date,
                                    "renewal_date" to mod.renewal_date,
                                    "premium_amount" to mod.premium_amount,
                                    "tpa_contact_number" to mod.tpa_contact_number,
                                    "company_number" to mod.company_help_desk_number,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    healthInsuranceList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.MOTOR_INSURANCE -> {
                    val mod = try {
                        list.mapToEntity(category) as MotorInsuranceModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        MotorInsuranceModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        MotorInsuranceModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "motor_insurance",
                                editId = "",
                                body = mapOf(
                                    "plan_name" to mod.plan_name,
                                    "policy_number" to mod.policy_number,
                                    "insured_declared_value" to mod.insured_declared_value,
                                    "type_start_cover" to mod.type_of_cover,
                                    "policy_holder_name" to mod.policy_holders_name,
                                    "policy_start_date" to mod.policy_start_date,
                                    "premium_amount" to mod.premium_amount,
                                    "renewal_date" to mod.renewal_date,
                                    "company_helpdesk_number" to mod.company_helpdesk_number,
                                    "nominee1" to mod.nominee_name,
                                    "relationship1" to mod.relationship,
                                    "allocation1" to mod.allocation_percent,
                                    "nominee2" to mod.nominee_name2_,
                                    "relationship2" to mod.relationship2,
                                    "allocation2" to mod.allocation_percent2,

                                    )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    motorInsuranceList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }
                AppData.LOAN -> {
                    val mod = try {
                        list.mapToEntity(category) as LoanModel
                    } catch ( e : IndexOutOfBoundsException){
                        Log.e(TAG, "addElement: Index out of bounds", )
                        LoanModel()
                    }
                    catch (e : ClassCastException){
                        Log.e(TAG, "addElement: Class Cast Exception", )
                        LoanModel()
                    }
                    viewModelScope.launch(Dispatchers.IO){
                        try {
                            val response = RetrofitClientInstance.retrofitService.postAssetRecorderData(
                                authToken = tokenSharedPreference,
                                assetType = "loan",
                                editId = "",
                                body = mapOf(
                                    "bank_name" to mod.bank_name,
                                    "loan_account_number" to mod.loan_account_number,
                                    "loan_amount" to mod.loan_amount,
                                    "asset_name" to mod.asset_name,
                                    "asset_type" to mod.asset_type,
                                    "loan_start_date" to mod.loan_start_date,
                                    "loan_tenure" to mod.loan_tenure,
                                    "emi_amount" to mod.emi_amount,
                                    "roi" to mod.roi,
                                    "ecs_bank" to mod.ecs_bank,
                                    "insurance_protect_loan" to mod.insurance_protect_loan,
                                    "current_outstanding" to mod.current_outstanding,
                                )

                            )
                            if (response.isSuccessful
                            ){
                                withContext(Dispatchers.Main){

                                    //call post api
                                    loanList.value[position] = mod
                                    result(true)
                                }
                            }
                            else {
                                Log.d(TAG, "addElement: Some problem :${response.body()}")
                                withContext(Dispatchers.Main){
                                    result(false)
                                }
                            }
                        } catch (e: Exception){
                            Log.e(TAG, "updateMfCategories: Error in updating categories :${e.localizedMessage}", )
                        }
                    }
                }


            }
        } catch (e: IndexOutOfBoundsException) {
            result(false)
        } catch (e: ClassCastException) {
            result(false)
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
            AppData.BANK_ACCOUNT -> {
                if (bankList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshBankList()
                } else {
                    _cardDataList.value = bankList.value.map {
                        LabelValue(
                            title = it.bank_name,
                            particular1 = it.branch_address,
                            particular2 = it.last_4_digit_of_account_number
                        )
                    }.toMutableList()
                }
            }
            AppData.MUTUAL_FUND -> {
                if (mutualList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshMutualList()
                } else {
                    _cardDataList.value = mutualList.value.map {
                        LabelValue(
                            title = it.purchase_amount,
                            particular1 = it.scheme_name,
                            particular2 = it.return_absolute
                        )
                    }.toMutableList()
                }
            }
            AppData.LOCKER -> {
                if (lockerList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshLockerList()
                } else {
                    _cardDataList.value = lockerList.value.map {
                        LabelValue(
                            title = it.bank_name,
                            particular1 = it.locker_number,
                            particular2 = it.bank_address
                        )
                    }.toMutableList()
                }
            }
            AppData.INDIAN_EQUITY -> {
                if (indianEquityList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshIndianEquityList()
                } else {
                    _cardDataList.value = indianEquityList.value.map {
                        LabelValue(
                            title = it.purchase_price,
                            particular1 = it.instrument_name,
                            particular2 = it.broker_name
                        )
                    }.toMutableList()
                }
            }
            AppData.US_EQUITY -> {
                if (usEquityList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshUsEquityList()
                } else {
                    _cardDataList.value = usEquityList.value.map {
                        LabelValue(
                            title = it.purchase_price,
                            particular1 = it.instrument_name,
                            particular2 = it.broker_name
                        )
                    }.toMutableList()
                }
            }
            AppData.CRYPTO -> {
                if (cryptoList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshCryptoList()
                } else {
                    _cardDataList.value = cryptoList.value.map {
                        LabelValue(
                            title = it.purchase_price,
                            particular1 = it.instrument_name,
                            particular2 = it.broker_name
                        )
                    }.toMutableList()
                }
            }
            AppData.BONDS -> {
                if (bondsList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshBondsList()
                } else {
                    _cardDataList.value = bondsList.value.map {
                        LabelValue(
                            title = it.purchase_price,
                            particular1 = it.instrument_name,
                            particular2 = it.broker_name
                        )
                    }.toMutableList()
                }
            }
            AppData.OFF_MARKET -> {
                if (offMarketList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshOffMarketList()
                } else {
                    _cardDataList.value = offMarketList.value.map {
                        LabelValue(
                            title = it.purchase_price,
                            particular1 = it.instrument_name,
                            particular2 = it.broker_name
                        )
                    }.toMutableList()
                }
            }
            AppData.AIF -> {
                if (aifList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshAifList()
                } else {
                    _cardDataList.value = aifList.value.map {
                        LabelValue(
                            title = it.invested_value,
                            particular1 = it.pms_aif_name,
                            particular2 = it.amc_name
                        )
                    }.toMutableList()
                }
            }
            AppData.PMS -> {
                if (pmsList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshPmsList()
                } else {
                    _cardDataList.value = pmsList.value.map {
                        LabelValue(
                            title = it.invested_value,
                            particular1 = it.pms_aif_name,
                            particular2 = it.amc_name
                        )
                    }.toMutableList()
                }
            }
            AppData.EPF -> {
                if (epfList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshEpfList()
                } else {
                    _cardDataList.value = epfList.value.map {
                        LabelValue(
                            title = it.balance,
                            particular1 = it.epf_no,
                            particular2 = it.nominee_name
                        )
                    }.toMutableList()
                }
            }
            AppData.PPF -> {
                if (ppfList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshPpfList()
                } else {
                    _cardDataList.value = ppfList.value.map {
                        LabelValue(
                            title = it.balance,
                            particular1 = it.bank_name,
                            particular2 = it.branch_address
                        )
                    }.toMutableList()
                }
            }
            AppData.REAL_ESTATE -> {
                if (realEstateList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshRealEstateList()
                } else {
                    _cardDataList.value = realEstateList.value.map {
                        LabelValue(
                            title = it.invested_value,
                            particular1 = it.property_name,
                            particular2 = it.location_pincode
                        )
                    }.toMutableList()
                }
            }
            AppData.NPS_TIER_1 -> {
                if (npsTierList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshNpsTierList()
                } else {
                    _cardDataList.value = npsTierList.value.map {
                        LabelValue(
                            title = it._current_balance,
                            particular1 = it.bank_details,
                            particular2 = it.pran
                        )
                    }.toMutableList()
                }
            }
            AppData.OTHERS -> {
                if (othersList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshOthersList()
                } else {
                    _cardDataList.value = othersList.value.map {
                        LabelValue(
                            title = it.purchase_value,
                            particular1 = it.instrument_name,
                            particular2 = it.no_of_units
                        )
                    }.toMutableList()
                }
            }
            AppData.LIFE_INSURANCE -> {
                if (lifeInsuranceList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshLifeInsuranceList()
                } else {
                    _cardDataList.value = lifeInsuranceList.value.map {
                        LabelValue(
                            title = it.sum_assured_,
                            particular1 = it.plan_name,
                            particular2 = it.premium_amount
                        )
                    }.toMutableList()
                }
            }
            AppData.HEALTH_INSURANCE -> {
                if (healthInsuranceList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshHealthInsuranceList()
                } else {
                    _cardDataList.value = healthInsuranceList.value.map {
                        LabelValue(
                            title = it.sum_assured_,
                            particular1 = it.plan_name,
                            particular2 = it.premium_amount
                        )
                    }.toMutableList()
                }
            }
            AppData.MOTOR_INSURANCE -> {
                if (motorInsuranceList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshMotorInsuranceList()
                } else {
                    _cardDataList.value = motorInsuranceList.value.map {
                        LabelValue(
                            title = it.plan_name,
                            particular1 = it.policy_number,
                            particular2 = it.premium_amount
                        )
                    }.toMutableList()
                }
            }
            AppData.LOAN -> {
                if (loanList.value.isEmpty()) {
                    _cardDataList.value.clear()
                    refreshLoanList()
                } else {
                    _cardDataList.value = loanList.value.map {
                        LabelValue(
                            title = it.loan_amount,
                            particular1 = it.bank_name,
                            particular2 = it.emi_amount
                        )
                    }.toMutableList()
                }
            }

        }
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
            AppData.BANK_ACCOUNT -> {
                val list = try {
                    if (position == -1) BankModel().mapToList(category) else bankList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.MUTUAL_FUND -> {
                val list = try {
                    if (position == -1) MutualModel().mapToList(category) else mutualList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.LOCKER -> {
                val list = try {
                    if (position == -1) LockerModel().mapToList(category) else lockerList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.INDIAN_EQUITY -> {
                val list = try {
                    if (position == -1) IndianEquityModel().mapToList(category) else indianEquityList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.US_EQUITY -> {
                val list = try {
                    if (position == -1) UsEquityModel().mapToList(category) else usEquityList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.CRYPTO -> {
                val list = try {
                    if (position == -1) CryptoModel().mapToList(category) else cryptoList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.BONDS -> {
                val list = try {
                    if (position == -1) BondsModel().mapToList(category) else bondsList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.OFF_MARKET -> {
                val list = try {
                    if (position == -1) OffMarketModel().mapToList(category) else offMarketList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.AIF -> {
                val list = try {
                    if (position == -1) AifModel().mapToList(category) else aifList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.PMS -> {
                val list = try {
                    if (position == -1) PmsModel().mapToList(category) else pmsList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.EPF -> {
                val list = try {
                    if (position == -1) EpfModel().mapToList(category) else epfList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.PPF -> {
                val list = try {
                    if (position == -1) PpfModel().mapToList(category) else ppfList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.REAL_ESTATE -> {
                val list = try {
                    if (position == -1) RealEstateModel().mapToList(category) else realEstateList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.NPS_TIER_1 -> {
                val list = try {
                    if (position == -1) NpsTierModel().mapToList(category) else npsTierList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.OTHERS -> {
                val list = try {
                    if (position == -1) OthersModel().mapToList(category) else othersList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.LIFE_INSURANCE -> {
                val list = try {
                    if (position == -1) LifeInsuranceModel().mapToList(category) else lifeInsuranceList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.HEALTH_INSURANCE -> {
                val list = try {
                    if (position == -1) HealthInsuranceModel().mapToList(category) else healthInsuranceList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.MOTOR_INSURANCE -> {
                val list = try {
                    if (position == -1) MotorInsuranceModel().mapToList(category) else motorInsuranceList.value[position].mapToList(
                        category
                    )
                } catch (e : IndexOutOfBoundsException){
                    Log.e(TAG, "getDataDetailsList: Index out of bounds", )
                    listOf()
                }
                list
            }
            AppData.LOAN -> {
                val list = try {
                    if (position == -1) LoanModel().mapToList(category) else loanList.value[position].mapToList(
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

    // REFRESHERS
    private fun refreshFixedDeposits() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "fd"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        _fdList.value = response.body()!!.map {dto ->
                                FixedDeposit(
                                    id = dto["id"] ?: "",
                                    bank_name = dto["bank_name"]  ?: "",
                                    amount_invested = dto["amount_invested"] ?: "",
                                    branch_addr = dto["branch_address"] ?: "",
                                    deposit_holders_name = dto["deposit_name"] ?: "",
                                    investment_date = dto["investment_date"] ?: "",
                                    investment_duration = dto["investment_duration"] ?: "",
                                    rate_of_interest = dto["rate_interest"] ?: "",
                                    maturity_date = dto["maturity_date"] ?: "",
                                    maturity_amount = dto["maturity_amount"] ?: "",
                                    nominee_name2 = dto["nominee2"] ?: "",
                                    nominee_name = dto["nominee1"] ?: "",
                                    relationship2 = dto["relationship2"] ?: "",
                                    relationship = dto["relationship1"] ?: "",
                                    allocation2 = dto["allocation2"] ?: "",
                                    allocation = dto["allocation1"] ?: ""
                                )

                        }.toMutableList()
                        //after populating
                        _cardDataList.value = _fdList.value.map {
                            LabelValue(
                                title = it.amount_invested, // I expect to get it in correct format i.e. having unit INR of needed
                                particular1 = it.bank_name,
                                particular2 = it.maturity_date
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshBankList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "bank_account"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        bankList.value = response.body()!!.map {dto ->
                            BankModel(
                                id = dto["id"] ?: "",
                                bank_name = dto["bank_name"] ?: "",
                                branch_address = dto["branch_address"] ?: "",
                                account_holder_name = dto["account_holder"] ?: "",
                                last_4_digit_of_account_number = dto["last_digit_account"] ?: "",
                                account_type = dto["account_type"] ?: "",
                                nominee_name = dto["nominee1"] ?: "",
                                relationship = dto["relationship1"] ?: "",
                                allocation_percent = dto["allocation1"] ?: "",
                                nominee_name2_ = dto["nominee2"] ?: "",
                                relationship2 = dto["relationship2"] ?: "",
                                allocation_percent2 = dto["allocation2"] ?: "",
                            )

                        }.toMutableList()
                        //after populating
                        //after populating
                        _cardDataList.value = bankList.value.map {
                            LabelValue(
                                title = it.bank_name,
                                particular1 = it.branch_address,
                                particular2 = it.last_4_digit_of_account_number
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshMutualList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "mf"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        mutualList.value = response.body()!!.map {dto ->
                        MutualModel(
                            id = dto["id"] ?: "",
                            scheme_name = dto["scheme_name"] ?: "",
                            purchase_amount = dto["purchase_amount"] ?: "",
                            purchase_nav = dto[" puchase_nav"] ?: "",
                            current_nav = dto["current_nav"] ?: "",
                            current_amount = dto["current_amount"] ?: "",
                            div_amount = dto["div_amount"] ?: "",
                            gain_short_term = dto["gain_short"] ?: "",
                            gain_long_term = dto["gain_long"] ?: "",
                            return_absolute = dto["return_abs"] ?: "",
                            return_cagr = dto["return_cagr"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = mutualList.value.map {
                            LabelValue(
                                title = it.purchase_amount,
                                particular1 = it.scheme_name,
                                particular2 = it.return_absolute
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshLockerList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "locker"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        lockerList.value = response.body()!!.map {dto ->
                        LockerModel(
                            id = dto["id"] ?: "",
                            bank_name = dto["bank_name"] ?: "",
                            bank_address = dto["bank_address"] ?: "",
                            locker_number = dto["locker_number"] ?: "",
                            locker_holder_name = dto["locker_name"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = lockerList.value.map {
                            LabelValue(
                                title = it.bank_name,
                                particular1 = it.locker_number,
                                particular2 = it.bank_address
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshIndianEquityList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "indian_equity"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        indianEquityList.value = response.body()!!.map {dto ->
                        IndianEquityModel(
                            id = dto["id"] ?: "",
                            broker_name = dto["broker_name"] ?: "",
                            account_number = dto["account_number"] ?: "",
                            instrument_name = dto["instrument_name"] ?: "",
                            qty = dto["qty"] ?: "",
                            purchase_price = dto["purchase_price"] ?: "",
                            purchase_value = dto["purchase_value"] ?: "",
                            market_price = dto["market_price"] ?: "",
                            market_value = dto["market_value"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )
                    }.toMutableList()
                        //after populating
                        _cardDataList.value = indianEquityList.value.map {
                            LabelValue(
                                title = it.purchase_price,
                                particular1 = it.instrument_name,
                                particular2 = it.broker_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshUsEquityList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "us_equity"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        usEquityList.value = response.body()!!.map {dto ->
                        UsEquityModel(
                            id = dto["id"] ?: "",
                            broker_name = dto["broker_name"] ?: "",
                            account_number = dto["account_number"] ?: "",
                            instrument_name = dto["instrument_name"] ?: "",
                            qty = dto["qty"] ?: "",
                            purchase_price = dto["purchase_price"] ?: "",
                            purchase_value = dto["purchase_value"] ?: "",
                            market_price = dto["market_price"] ?: "",
                            market_value = dto["market_value"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        //after populating
                        _cardDataList.value = usEquityList.value.map {
                            LabelValue(
                                title = it.purchase_price,
                                particular1 = it.instrument_name,
                                particular2 = it.broker_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshCryptoList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "crypto"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        cryptoList.value = response.body()!!.map {dto ->
                        CryptoModel(
                            id = dto["id"] ?: "",
                            broker_name = dto["broker_name"] ?: "",
                            account_number = dto["account_number"] ?: "",
                            instrument_name = dto["instrument_name"] ?: "",
                            qty = dto["qty"] ?: "",
                            purchase_price = dto["purchase_price"] ?: "",
                            purchase_value = dto["purchase_value"] ?: "",
                            market_price = dto["market_price"] ?: "",
                            market_value = dto["market_value"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = cryptoList.value.map {
                            LabelValue(
                                title = it.purchase_price,
                                particular1 = it.instrument_name,
                                particular2 = it.broker_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshBondsList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "bonds"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        bondsList.value = response.body()!!.map {dto ->
                        BondsModel(
                            id = dto["id"] ?: "",
                            broker_name = dto["broker_name"] ?: "",
                            account_number = dto["account_number"] ?: "",
                            instrument_name = dto["instrument_name"] ?: "",
                            qty = dto["qty"] ?: "",
                            purchase_price = dto["purchase_price"] ?: "",
                            purchase_value = dto["purchase_value"] ?: "",
                            market_price = dto["market_price"] ?: "",
                            market_value = dto["market_value"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = bondsList.value.map {
                            LabelValue(
                                title = it.purchase_price,
                                particular1 = it.instrument_name,
                                particular2 = it.broker_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshOffMarketList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "off_market"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        offMarketList.value = response.body()!!.map {dto ->
                        OffMarketModel(
                            id = dto["id"] ?: "",
                            broker_name = dto["broker_name"] ?: "",
                            account_number = dto["account_number"] ?: "",
                            instrument_name = dto["instrument_name"] ?: "",
                            qty = dto["qty"] ?: "",
                            purchase_price = dto["purchase_price"] ?: "",
                            purchase_value = dto["purchase_value"] ?: "",
                            market_price = dto["market_price"] ?: "",
                            market_value = dto["market_value"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = offMarketList.value.map {
                            LabelValue(
                                title = it.purchase_price,
                                particular1 = it.instrument_name,
                                particular2 = it.broker_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshAifList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "aif"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        aifList.value = response.body()!!.map {dto ->
                        AifModel(
                            id = dto["id"] ?: "",
                            amc_name = dto["amc_name"] ?: "",
                            pms_aif_name = dto["pms_name"] ?: "",
                            invested_value = dto["interested_value"] ?: "",
                            current_amount = dto["current_amount"] ?: "",
                            account_number = dto["account_number"] ?: "",
                            invested_date = dto["invested_date"] ?: "",
                            return_absolute = dto["return_abs"] ?: "",
                            return_cagr = dto["return_cagr"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = aifList.value.map {
                            LabelValue(
                                title = it.invested_value,
                                particular1 = it.pms_aif_name,
                                particular2 = it.amc_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshPmsList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "pms"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        pmsList.value = response.body()!!.map {dto ->
                        PmsModel(
                            id = dto["id"] ?: "",
                            amc_name = dto["amc_name"] ?: "",
                            pms_aif_name = dto["pms_name"] ?: "",
                            invested_value = dto["interested_value"] ?: "",
                            current_amount = dto["current_amount"] ?: "",
                            account_number = dto["account_number"] ?: "",
                            invested_date = dto["invested_date"] ?: "",
                            return_absolute = dto["return_abs"] ?: "",
                            return_cagr = dto["return_cagr"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = pmsList.value.map {
                            LabelValue(
                                title = it.invested_value,
                                particular1 = it.pms_aif_name,
                                particular2 = it.amc_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshEpfList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "epf"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        epfList.value = response.body()!!.map {dto ->
                        EpfModel(
                            id = dto["id"] ?: "",
                            epf_no = dto["epf_number"] ?: "",
                            balance = dto["balance"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = epfList.value.map {
                            LabelValue(
                                title = it.balance,
                                particular1 = it.epf_no,
                                particular2 = it.nominee_name
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshPpfList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "ppf"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        ppfList.value = response.body()!!.map {dto ->
                        PpfModel(
                            id = dto["id"] ?: "",
                            bank_name = dto["bank_name"] ?: "",
                            branch_address = dto["branch_address"] ?: "",
                            ppf_account_no = dto["ppf_account_number"] ?: "",
                            balance = dto["balance"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",

                            )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = ppfList.value.map {
                            LabelValue(
                                title = it.balance,
                                particular1 = it.bank_name,
                                particular2 = it.branch_address
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshRealEstateList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "real_estate"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        realEstateList.value = response.body()!!.map {dto ->
                        RealEstateModel(
                            id = dto["id"] ?: "",
                            property_name = dto["property_name"] ?: "",
                            invested_value = dto["invested_value"] ?: "",
                            investment_date = dto["invested_date"] ?: "",
                            current_value = dto["current_value"] ?: "",
                            property_type = dto["property_type"] ?: "",
                            property_size = dto["property_size"] ?: "",
                            location_pincode = dto["location_pincode"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",
                        )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = realEstateList.value.map {
                            LabelValue(
                                title = it.invested_value,
                                particular1 = it.property_name,
                                particular2 = it.location_pincode
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshNpsTierList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "nps_tier1"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        npsTierList.value = response.body()!!.map {dto ->
                        NpsTierModel(
                            id = dto["id"] ?: "",
                            pran = dto["pran"] ?: "",
                            _current_balance = dto["current_balance"] ?: "",
                            type_of_account_ = dto["account_type"] ?: "",
                            nominee_details = dto["nominee_details"] ?: "",
                            bank_details = dto["bank_details"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",
                        )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = npsTierList.value.map {
                            LabelValue(
                                title = it._current_balance,
                                particular1 = it.bank_details,
                                particular2 = it.pran
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshOthersList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "others"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        othersList.value = response.body()!!.map {dto ->
                        OthersModel(
                            id = dto["id"] ?: "",
                            instrument_name = dto["instrument_name"] ?: "",
                            purchase_value = dto["purchase_value"] ?: "",
                            current_current = dto["current_value"] ?: "",
                            purchase_date = dto["purchase_date"] ?: "",
                            no_of_units = dto["units"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",
                        )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = othersList.value.map {
                            LabelValue(
                                title = it.purchase_value,
                                particular1 = it.instrument_name,
                                particular2 = it.no_of_units
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshLifeInsuranceList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "life_insurance"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        lifeInsuranceList.value = response.body()!!.map {dto ->
                        LifeInsuranceModel(
                            id = dto["id"] ?: "",
                            plan_name = dto["plan_name"] ?: "",
                            policy_number = dto["policy_number"] ?: "",
                            policy_holders_name = dto["policy_holder_name"] ?: "",
                            sum_assured_ = dto["sum_assured"] ?: "",
                            policy_start_date = dto["policy_start_date"] ?: "",
                            policy_maturity_date = dto["policy_maturity_date"] ?: "",
                            policy_term_years = dto["policy_term"] ?: "",
                            premium_payment_term_years = dto["premium_payment"] ?: "",
                            premium_amount = dto["premium_amount"] ?: "",
                            premium_payment_frequency_ = dto["premium_payment_frequency"] ?: "",
                            renewal_date = dto["renewal_date"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",
                        )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = lifeInsuranceList.value.map {
                            LabelValue(
                                title = it.sum_assured_,
                                particular1 = it.plan_name,
                                particular2 = it.premium_amount
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshHealthInsuranceList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "health_insurance"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        healthInsuranceList.value = response.body()!!.map {dto ->
                        HealthInsuranceModel(
                            id = dto["id"] ?: "",
                            plan_name = dto["plan_name"] ?: "",
                            policy_number = dto["policy_number"] ?: "",
                            policy_holders_name = dto["policy_holder_name"] ?: "",
                            family_member_covered = dto["family_member _covered"] ?: "",
                            sum_assured_ = dto["sum_assured"] ?: "",
                            policy_start_date = dto["policy_start_date"] ?: "",
                            renewal_date = dto["renewal_date"] ?: "",
                            premium_amount = dto["premium_amount"] ?: "",
                            tpa_contact_number = dto["tpa_contact_number"] ?: "",
                            company_help_desk_number = dto["company_number"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",
                        )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = healthInsuranceList.value.map {
                            LabelValue(
                                title = it.sum_assured_,
                                particular1 = it.plan_name,
                                particular2 = it.premium_amount
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshMotorInsuranceList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "motor_insurance"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        motorInsuranceList.value = response.body()!!.map {dto ->
                        MotorInsuranceModel(
                            id = dto["id"] ?: "",
                            plan_name = dto["plan_name"] ?: "",
                            policy_number = dto["policy_number"] ?: "",
                            insured_declared_value = dto["insured_declared_value"] ?: "",
                            type_of_cover = dto["type_start_cover"] ?: "",
                            policy_holders_name = dto["policy_holder_name"] ?: "",
                            policy_start_date = dto["policy_start_date"] ?: "",
                            premium_amount = dto["premium_amount"] ?: "",
                            renewal_date = dto["renewal_date"] ?: "",
                            company_helpdesk_number = dto["company_helpdesk_number"] ?: "",
                            nominee_name = dto["nominee1"] ?: "",
                            relationship = dto["relationship1"] ?: "",
                            allocation_percent = dto["allocation1"] ?: "",
                            nominee_name2_ = dto["nominee2"] ?: "",
                            relationship2 = dto["relationship2"] ?: "",
                            allocation_percent2 = dto["allocation2"] ?: "",
                        )

                    }.toMutableList()
                        //after populating
                        _cardDataList.value = motorInsuranceList.value.map {
                            LabelValue(
                                title = it.plan_name,
                                particular1 = it.policy_number,
                                particular2 = it.premium_amount
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }
    private fun refreshLoanList() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val response = RetrofitClientInstance.retrofitService.getAssetRecorderData(
                    authToken = tokenSharedPreference,
                    assetType = "loan"
                )
                if (response.isSuccessful && response.body() != null
                ){
                    withContext(Dispatchers.Main){
                        //call post api
                        loanList.value = response.body()!!.map {dto ->
                        LoanModel(
                            id = dto["id"] ?: "",
                            bank_name = dto["bank_name"] ?: "",
                            loan_account_number = dto["loan_account_number"] ?: "",
                            loan_amount = dto["loan_amount"] ?: "",
                            asset_name = dto["asset_name"] ?: "",
                            asset_type = dto["asset_type"] ?: "",
                            loan_start_date = dto["loan_start_date"] ?: "",
                            loan_tenure = dto["loan_tenure"] ?: "",
                            emi_amount = dto["emi_amount"] ?: "",
                            roi = dto["roi"] ?: "",
                            ecs_bank = dto["ecs_bank"] ?: "",
                            insurance_protect_loan = dto["insurance_protect_loan"] ?: "",
                            current_outstanding = dto["current_outstanding"] ?: "",
                        )

                    }.toMutableList()
                        //after populating
                        //after populating
                        _cardDataList.value = loanList.value.map {
                            LabelValue(
                                title = it.loan_amount,
                                particular1 = it.bank_name,
                                particular2 = it.emi_amount
                            )
                        }.toMutableList()
                    }
                }
                else {
                    Log.d(TAG, "addElement: Some problem :${response.body()}")

                }
            } catch (e: Exception){
                Log.e(TAG, "Error in refreshing :${e.localizedMessage}", )
            }
        }

    }



}