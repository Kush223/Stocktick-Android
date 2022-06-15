package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.ListEntityMapper.mapToEntity
import com.example.stocktick.ui.mutual_funds.asset_recorder.ListEntityMapper.mapToList
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.EditDetailsModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.LabelValue
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.*
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This view model will have all the dynamic data required in asset recorder section*/
private const val TAG = "FdViewModel"

class AssetDataViewModel(application: Application) : AndroidViewModel(application) {

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
                try {
                    _fdList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.BANK_ACCOUNT -> {
                try {
                    bankList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.MUTUAL_FUND -> {
                try {
                    mutualList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.LOCKER -> {
                try {
                    lockerList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.INDIAN_EQUITY -> {
                try {
                    indianEquityList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.US_EQUITY -> {
                try {
                    usEquityList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.CRYPTO -> {
                try {
                    cryptoList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.BONDS -> {
                try {
                    bondsList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.OFF_MARKET -> {
                try {
                    offMarketList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.AIF -> {
                try {
                    aifList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.PMS -> {
                try {
                    pmsList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.EPF -> {
                try {
                    epfList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.PPF -> {
                try {
                    ppfList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.REAL_ESTATE -> {
                try {
                    realEstateList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.NPS_TIER_1 -> {
                try {
                    npsTierList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.OTHERS -> {
                try {
                    othersList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.LIFE_INSURANCE -> {
                try {
                    lifeInsuranceList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.HEALTH_INSURANCE -> {
                try {
                    healthInsuranceList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.MOTOR_INSURANCE -> {
                try {
                    motorInsuranceList.value.removeAt(position)
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "Delete element : Index out of bounds")
                }
                result(true)
            }
            AppData.LOAN -> {
                try {
                    loanList.value.removeAt(position)
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
                    //call post api
                    bankList.value.add(mod)
                    result(true)
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
                    //call post api
                    mutualList.value.add(mod)
                    result(true)
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
                    //call post api
                    lockerList.value.add(mod)
                    result(true)
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
                    //call post api
                    indianEquityList.value.add(mod)
                    result(true)
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
                    //call post api
                    usEquityList.value.add(mod)
                    result(true)
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
                    //call post api
                    cryptoList.value.add(mod)
                    result(true)
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
                    //call post api
                    bondsList.value.add(mod)
                    result(true)
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
                    //call post api
                    offMarketList.value.add(mod)
                    result(true)
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
                    //call post api
                    aifList.value.add(mod)
                    result(true)
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
                    //call post api
                    pmsList.value.add(mod)
                    result(true)
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
                    //call post api
                    epfList.value.add(mod)
                    result(true)
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
                    //call post api
                    ppfList.value.add(mod)
                    result(true)
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
                    //call post api
                    realEstateList.value.add(mod)
                    result(true)
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
                    //call post api
                    npsTierList.value.add(mod)
                    result(true)
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
                    //call post api
                    othersList.value.add(mod)
                    result(true)
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
                    //call post api
                    lifeInsuranceList.value.add(mod)
                    result(true)
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
                    //call post api
                    healthInsuranceList.value.add(mod)
                    result(true)
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
                    //call post api
                    motorInsuranceList.value.add(mod)
                    result(true)
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
                    //call post api
                    loanList.value.add(mod)
                    result(true)
                }


            }
        } catch (e: IndexOutOfBoundsException) {
            result(false)
        } catch (e: ClassCastException) {
            result(false)
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
                    //call post api
                    bankList.value[position] = mod
                    result(true)
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
                    //call post api
                    mutualList.value[position] = mod
                    result(true)
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
                    //call post api
                    lockerList.value[position] = mod
                    result(true)
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
                    //call post api
                    indianEquityList.value[position] = mod
                    result(true)
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
                    //call post api
                    usEquityList.value[position] = mod
                    result(true)
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
                    //call post api
                    cryptoList.value[position] = mod
                    result(true)
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
                    //call post api
                    bondsList.value[position] = mod
                    result(true)
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
                    //call post api
                    offMarketList.value[position] = mod
                    result(true)
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
                    //call post api
                    aifList.value[position] = mod
                    result(true)
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
                    //call post api
                    pmsList.value[position] = mod
                    result(true)
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
                    //call post api
                    epfList.value[position] = mod
                    result(true)
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
                    //call post api
                    ppfList.value[position] = mod
                    result(true)
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
                    //call post api
                    realEstateList.value[position] = mod
                    result(true)
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
                    //call post api
                    npsTierList.value[position] = mod
                    result(true)
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
                    //call post api
                    othersList.value[position] = mod
                    result(true)
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
                    //call post api
                    lifeInsuranceList.value[position] = mod
                    result(true)
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
                    //call post api
                    healthInsuranceList.value[position] = mod
                    result(true)
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
                    //call post api
                    motorInsuranceList.value[position] = mod
                    result(true)
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
                    //call post api
                    loanList.value[position] = mod
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

    private fun refreshBankList() {

        //after populating
        _cardDataList.value = bankList.value.map {
            LabelValue(
                title = it.bank_name,
                particular1 = it.branch_address,
                particular2 = it.last_4_digit_of_account_number
            )
        }.toMutableList()
    }
    private fun refreshMutualList() {

        //after populating
        _cardDataList.value = mutualList.value.map {
            LabelValue(
                title = it.purchase_amount,
                particular1 = it.scheme_name,
                particular2 = it.return_absolute
            )
        }.toMutableList()
    }
    private fun refreshLockerList() {

        //after populating
        _cardDataList.value = lockerList.value.map {
            LabelValue(
                title = it.bank_name,
                particular1 = it.locker_number,
                particular2 = it.bank_address
            )
        }.toMutableList()
    }
    private fun refreshIndianEquityList() {

        //after populating
        _cardDataList.value = indianEquityList.value.map {
            LabelValue(
                title = it.purchase_price,
                particular1 = it.instrument_name,
                particular2 = it.broker_name
            )
        }.toMutableList()
    }
    private fun refreshUsEquityList() {

        //after populating
        _cardDataList.value = usEquityList.value.map {
            LabelValue(
                title = it.purchase_price,
                particular1 = it.instrument_name,
                particular2 = it.broker_name
            )
        }.toMutableList()
    }
    private fun refreshCryptoList() {

        //after populating
        _cardDataList.value = cryptoList.value.map {
            LabelValue(
                title = it.purchase_price,
                particular1 = it.instrument_name,
                particular2 = it.broker_name
            )
        }.toMutableList()
    }
    private fun refreshBondsList() {

        //after populating
        _cardDataList.value = bondsList.value.map {
            LabelValue(
                title = it.purchase_price,
                particular1 = it.instrument_name,
                particular2 = it.broker_name
            )
        }.toMutableList()
    }
    private fun refreshOffMarketList() {

        //after populating
        _cardDataList.value = offMarketList.value.map {
            LabelValue(
                title = it.purchase_price,
                particular1 = it.instrument_name,
                particular2 = it.broker_name
            )
        }.toMutableList()
    }
    private fun refreshAifList() {

        //after populating
        _cardDataList.value = aifList.value.map {
            LabelValue(
                title = it.invested_value,
                particular1 = it.pms_aif_name,
                particular2 = it.amc_name
            )
        }.toMutableList()
    }
    private fun refreshPmsList() {

        //after populating
        _cardDataList.value = pmsList.value.map {
            LabelValue(
                title = it.invested_value,
                particular1 = it.pms_aif_name,
                particular2 = it.amc_name
            )
        }.toMutableList()
    }
    private fun refreshEpfList() {

        //after populating
        _cardDataList.value = epfList.value.map {
            LabelValue(
                title = it.balance,
                particular1 = it.epf_no,
                particular2 = it.nominee_name
            )
        }.toMutableList()
    }
    private fun refreshPpfList() {

        //after populating
        _cardDataList.value = ppfList.value.map {
            LabelValue(
                title = it.balance,
                particular1 = it.bank_name,
                particular2 = it.branch_address
            )
        }.toMutableList()
    }
    private fun refreshRealEstateList() {

        //after populating
        _cardDataList.value = realEstateList.value.map {
            LabelValue(
                title = it.invested_value,
                particular1 = it.property_name,
                particular2 = it.location_pincode
            )
        }.toMutableList()
    }
    private fun refreshNpsTierList() {

        //after populating
        _cardDataList.value = npsTierList.value.map {
            LabelValue(
                title = it._current_balance,
                particular1 = it.bank_details,
                particular2 = it.pran
            )
        }.toMutableList()
    }
    private fun refreshOthersList() {

        //after populating
        _cardDataList.value = othersList.value.map {
            LabelValue(
                title = it.purchase_value,
                particular1 = it.instrument_name,
                particular2 = it.no_of_units
            )
        }.toMutableList()
    }
    private fun refreshLifeInsuranceList() {

        //after populating
        _cardDataList.value = lifeInsuranceList.value.map {
            LabelValue(
                title = it.sum_assured_,
                particular1 = it.plan_name,
                particular2 = it.premium_amount
            )
        }.toMutableList()
    }
    private fun refreshHealthInsuranceList() {

        //after populating
        _cardDataList.value = healthInsuranceList.value.map {
            LabelValue(
                title = it.sum_assured_,
                particular1 = it.plan_name,
                particular2 = it.premium_amount
            )
        }.toMutableList()
    }
    private fun refreshMotorInsuranceList() {

        //after populating
        _cardDataList.value = motorInsuranceList.value.map {
            LabelValue(
                title = it.plan_name,
                particular1 = it.policy_number,
                particular2 = it.premium_amount
            )
        }.toMutableList()
    }
    private fun refreshLoanList() {

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