package com.example.stocktick.ui.mutual_funds.asset_recorder

import android.util.Log
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.app_model.EditDetailsModel
import com.example.stocktick.ui.mutual_funds.asset_recorder.models.domain.*

private const val TAG = "ListEntityMapper"
object ListEntityMapper {

    fun List<EditDetailsModel>.mapToEntity(category : Int) : Any? {
        when (category) {
            AppData.FIXED_DEPOSIT -> {
                val fd = FixedDeposit()
                try {
                    this[0].text.isFine {
                        fd.bank_name = it
                    }
                    this[1].text.isFine {
                        fd.branch_addr = it
                    }
                    this[2].text.isFine {
                        fd.deposit_holders_name = it
                    }
                    this[3].text.isFine {
                        fd.amount_invested = it
                    }
                    this[4].text.isFine {
                        fd.investment_date = it
                    }
                    this[5].text.isFine {
                        fd.investment_duration = it
                    }
                    this[6].text.isFine {
                        fd.rate_of_interest = it
                    }
                    this[7].text.isFine {
                        fd.maturity_date = it
                    }
                    this[8].text.isFine {
                        fd.maturity_amount = it
                    }
                    this[9].text.isFine {
                        fd.nominee_name = it
                    }
                    this[10].text.isFine {
                        fd.relationship = it
                    }
                    this[11].text.isFine {
                        fd.allocation = it
                    }
                    this[12].text.isFine {
                        fd.nominee_name2 = it
                    }
                    this[13].text.isFine {
                        fd.relationship2 = it
                    }
                    this[14].text.isFine {
                        fd.allocation2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return fd
                }
            }
            AppData.BANK_ACCOUNT ->{
                val mod = BankModel()
                try {                    this[0].text.isFine {
                    mod.bank_name = it
                }
                    this[1].text.isFine {
                        mod.branch_address = it
                    }
                    this[2].text.isFine {
                        mod.account_holder_name = it
                    }
                    this[3].text.isFine {
                        mod.last_4_digit_of_account_number = it
                    }
                    this[4].text.isFine {
                        mod.account_type = it
                    }
                    this[5].text.isFine {
                        mod.nominee_name = it
                    }
                    this[6].text.isFine {
                        mod.relationship = it
                    }
                    this[7].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[9].text.isFine {
                        mod.relationship2 = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.MUTUAL_FUND ->{
                val mod = MutualModel()
                try {                    this[0].text.isFine {
                    mod.scheme_name = it
                }
                    this[1].text.isFine {
                        mod.purchase_amount = it
                    }
                    this[2].text.isFine {
                        mod.purchase_nav = it
                    }
                    this[3].text.isFine {
                        mod.current_nav = it
                    }
                    this[4].text.isFine {
                        mod.current_amount = it
                    }
                    this[5].text.isFine {
                        mod.div_amount = it
                    }
                    this[6].text.isFine {
                        mod.gain_short_term = it
                    }
                    this[7].text.isFine {
                        mod.gain_long_term = it
                    }
                    this[8].text.isFine {
                        mod.return_absolute = it
                    }
                    this[9].text.isFine {
                        mod.return_cagr = it
                    }
                    this[10].text.isFine {
                        mod.nominee_name = it
                    }
                    this[11].text.isFine {
                        mod.relationship = it
                    }
                    this[12].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[13].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[14].text.isFine {
                        mod.relationship2 = it
                    }
                    this[15].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.LOCKER ->{
                val mod = LockerModel()
                try {                    this[0].text.isFine {
                    mod.bank_name = it
                }
                    this[1].text.isFine {
                        mod.bank_address = it
                    }
                    this[2].text.isFine {
                        mod.locker_number = it
                    }
                    this[3].text.isFine {
                        mod.locker_holder_name = it
                    }
                    this[4].text.isFine {
                        mod.nominee_name = it
                    }
                    this[5].text.isFine {
                        mod.relationship = it
                    }
                    this[6].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[7].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[8].text.isFine {
                        mod.relationship2 = it
                    }
                    this[9].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.INDIAN_EQUITY ->{
                val mod = IndianEquityModel()
                try {                    this[0].text.isFine {
                    mod.broker_name = it
                }
                    this[1].text.isFine {
                        mod.account_number = it
                    }
                    this[2].text.isFine {
                        mod.instrument_name = it
                    }
                    this[3].text.isFine {
                        mod.qty = it
                    }
                    this[4].text.isFine {
                        mod.purchase_price = it
                    }
                    this[5].text.isFine {
                        mod.purchase_value = it
                    }
                    this[6].text.isFine {
                        mod.market_price = it
                    }
                    this[7].text.isFine {
                        mod.market_value = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name = it
                    }
                    this[9].text.isFine {
                        mod.relationship = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[12].text.isFine {
                        mod.relationship2 = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.US_EQUITY ->{
                val mod = UsEquityModel()
                try {                    this[0].text.isFine {
                    mod.broker_name = it
                }
                    this[1].text.isFine {
                        mod.account_number = it
                    }
                    this[2].text.isFine {
                        mod.instrument_name = it
                    }
                    this[3].text.isFine {
                        mod.qty = it
                    }
                    this[4].text.isFine {
                        mod.purchase_price = it
                    }
                    this[5].text.isFine {
                        mod.purchase_value = it
                    }
                    this[6].text.isFine {
                        mod.market_price = it
                    }
                    this[7].text.isFine {
                        mod.market_value = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name = it
                    }
                    this[9].text.isFine {
                        mod.relationship = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[12].text.isFine {
                        mod.relationship2 = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.CRYPTO ->{
                val mod = CryptoModel()
                try {                    this[0].text.isFine {
                    mod.broker_name = it
                }
                    this[1].text.isFine {
                        mod.account_number = it
                    }
                    this[2].text.isFine {
                        mod.instrument_name = it
                    }
                    this[3].text.isFine {
                        mod.qty = it
                    }
                    this[4].text.isFine {
                        mod.purchase_price = it
                    }
                    this[5].text.isFine {
                        mod.purchase_value = it
                    }
                    this[6].text.isFine {
                        mod.market_price = it
                    }
                    this[7].text.isFine {
                        mod.market_value = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name = it
                    }
                    this[9].text.isFine {
                        mod.relationship = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[12].text.isFine {
                        mod.relationship2 = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.BONDS ->{
                val mod = BondsModel()
                try {                    this[0].text.isFine {
                    mod.broker_name = it
                }
                    this[1].text.isFine {
                        mod.account_number = it
                    }
                    this[2].text.isFine {
                        mod.instrument_name = it
                    }
                    this[3].text.isFine {
                        mod.qty = it
                    }
                    this[4].text.isFine {
                        mod.purchase_price = it
                    }
                    this[5].text.isFine {
                        mod.purchase_value = it
                    }
                    this[6].text.isFine {
                        mod.market_price = it
                    }
                    this[7].text.isFine {
                        mod.market_value = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name = it
                    }
                    this[9].text.isFine {
                        mod.relationship = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[12].text.isFine {
                        mod.relationship2 = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.OFF_MARKET ->{
                val mod = OffMarketModel()
                try {                    this[0].text.isFine {
                    mod.broker_name = it
                }
                    this[1].text.isFine {
                        mod.account_number = it
                    }
                    this[2].text.isFine {
                        mod.instrument_name = it
                    }
                    this[3].text.isFine {
                        mod.qty = it
                    }
                    this[4].text.isFine {
                        mod.purchase_price = it
                    }
                    this[5].text.isFine {
                        mod.purchase_value = it
                    }
                    this[6].text.isFine {
                        mod.market_price = it
                    }
                    this[7].text.isFine {
                        mod.market_value = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name = it
                    }
                    this[9].text.isFine {
                        mod.relationship = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[12].text.isFine {
                        mod.relationship2 = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.AIF ->{
                val mod = AifModel()
                try {                    this[0].text.isFine {
                    mod.amc_name = it
                }
                    this[1].text.isFine {
                        mod.pms_aif_name = it
                    }
                    this[2].text.isFine {
                        mod.invested_value = it
                    }
                    this[3].text.isFine {
                        mod.current_amount = it
                    }
                    this[4].text.isFine {
                        mod.account_number = it
                    }
                    this[5].text.isFine {
                        mod.invested_date = it
                    }
                    this[6].text.isFine {
                        mod.return_absolute = it
                    }
                    this[7].text.isFine {
                        mod.return_cagr = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name = it
                    }
                    this[9].text.isFine {
                        mod.relationship = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[12].text.isFine {
                        mod.relationship2 = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.PMS ->{
                val mod = PmsModel()
                try {                    this[0].text.isFine {
                    mod.amc_name = it
                }
                    this[1].text.isFine {
                        mod.pms_aif_name = it
                    }
                    this[2].text.isFine {
                        mod.invested_value = it
                    }
                    this[3].text.isFine {
                        mod.current_amount = it
                    }
                    this[4].text.isFine {
                        mod.account_number = it
                    }
                    this[5].text.isFine {
                        mod.invested_date = it
                    }
                    this[6].text.isFine {
                        mod.return_absolute = it
                    }
                    this[7].text.isFine {
                        mod.return_cagr = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name = it
                    }
                    this[9].text.isFine {
                        mod.relationship = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[12].text.isFine {
                        mod.relationship2 = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.EPF ->{
                val mod = EpfModel()
                try {                    this[0].text.isFine {
                    mod.epf_no = it
                }
                    this[1].text.isFine {
                        mod.balance = it
                    }
                    this[2].text.isFine {
                        mod.nominee_name = it
                    }
                    this[3].text.isFine {
                        mod.relationship = it
                    }
                    this[4].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[5].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[6].text.isFine {
                        mod.relationship2 = it
                    }
                    this[7].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.PPF ->{
                val mod = PpfModel()
                try {                    this[0].text.isFine {
                    mod.bank_name = it
                }
                    this[1].text.isFine {
                        mod.branch_address = it
                    }
                    this[2].text.isFine {
                        mod.ppf_account_no = it
                    }
                    this[3].text.isFine {
                        mod.balance = it
                    }
                    this[4].text.isFine {
                        mod.nominee_name = it
                    }
                    this[5].text.isFine {
                        mod.relationship = it
                    }
                    this[6].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[7].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[8].text.isFine {
                        mod.relationship2 = it
                    }
                    this[9].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.REAL_ESTATE ->{
                val mod = RealEstateModel()
                try {                    this[0].text.isFine {
                    mod.property_name = it
                }
                    this[1].text.isFine {
                        mod.invested_value = it
                    }
                    this[2].text.isFine {
                        mod.investment_date = it
                    }
                    this[3].text.isFine {
                        mod.current_value = it
                    }
                    this[4].text.isFine {
                        mod.property_type = it
                    }
                    this[5].text.isFine {
                        mod.property_size = it
                    }
                    this[6].text.isFine {
                        mod.location_pincode = it
                    }
                    this[7].text.isFine {
                        mod.nominee_name = it
                    }
                    this[8].text.isFine {
                        mod.relationship = it
                    }
                    this[9].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[10].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[11].text.isFine {
                        mod.relationship2 = it
                    }
                    this[12].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.NPS_TIER_1 ->{
                val mod = NpsTierModel()
                try {                    this[0].text.isFine {
                    mod.pran = it
                }
                    this[1].text.isFine {
                        mod._current_balance = it
                    }
                    this[2].text.isFine {
                        mod.type_of_account_ = it
                    }
                    this[3].text.isFine {
                        mod.nominee_details = it
                    }
                    this[4].text.isFine {
                        mod.bank_details = it
                    }
                    this[5].text.isFine {
                        mod.nominee_name = it
                    }
                    this[6].text.isFine {
                        mod.relationship = it
                    }
                    this[7].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[9].text.isFine {
                        mod.relationship2 = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.OTHERS ->{
                val mod = OthersModel()
                try {                    this[0].text.isFine {
                    mod.instrument_name = it
                }
                    this[1].text.isFine {
                        mod.purchase_value = it
                    }
                    this[2].text.isFine {
                        mod.current_current = it
                    }
                    this[3].text.isFine {
                        mod.purchase_date = it
                    }
                    this[4].text.isFine {
                        mod.no_of_units = it
                    }
                    this[5].text.isFine {
                        mod.nominee_name = it
                    }
                    this[6].text.isFine {
                        mod.relationship = it
                    }
                    this[7].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[8].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[9].text.isFine {
                        mod.relationship2 = it
                    }
                    this[10].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.LIFE_INSURANCE ->{
                val mod = LifeInsuranceModel()
                try {                    this[0].text.isFine {
                    mod.plan_name = it
                }
                    this[1].text.isFine {
                        mod.policy_number = it
                    }
                    this[2].text.isFine {
                        mod.policy_holders_name = it
                    }
                    this[3].text.isFine {
                        mod.sum_assured_ = it
                    }
                    this[4].text.isFine {
                        mod.policy_start_date = it
                    }
                    this[5].text.isFine {
                        mod.policy_maturity_date = it
                    }
                    this[6].text.isFine {
                        mod.policy_term_years = it
                    }
                    this[7].text.isFine {
                        mod.premium_payment_term_years = it
                    }
                    this[8].text.isFine {
                        mod.premium_amount = it
                    }
                    this[9].text.isFine {
                        mod.premium_payment_frequency_ = it
                    }
                    this[10].text.isFine {
                        mod.renewal_date = it
                    }
                    this[11].text.isFine {
                        mod.nominee_name = it
                    }
                    this[12].text.isFine {
                        mod.relationship = it
                    }
                    this[13].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[14].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[15].text.isFine {
                        mod.relationship2 = it
                    }
                    this[16].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.HEALTH_INSURANCE ->{
                val mod = HealthInsuranceModel()
                try {                    this[0].text.isFine {
                    mod.plan_name = it
                }
                    this[1].text.isFine {
                        mod.policy_number = it
                    }
                    this[2].text.isFine {
                        mod.policy_holders_name = it
                    }
                    this[3].text.isFine {
                        mod.family_member_covered = it
                    }
                    this[4].text.isFine {
                        mod.sum_assured_ = it
                    }
                    this[5].text.isFine {
                        mod.policy_start_date = it
                    }
                    this[6].text.isFine {
                        mod.renewal_date = it
                    }
                    this[7].text.isFine {
                        mod.premium_amount = it
                    }
                    this[8].text.isFine {
                        mod.tpa_contact_number = it
                    }
                    this[9].text.isFine {
                        mod.company_help_desk_number = it
                    }
                    this[10].text.isFine {
                        mod.nominee_name = it
                    }
                    this[11].text.isFine {
                        mod.relationship = it
                    }
                    this[12].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[13].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[14].text.isFine {
                        mod.relationship2 = it
                    }
                    this[15].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.MOTOR_INSURANCE ->{
                val mod = MotorInsuranceModel()
                try {                    this[0].text.isFine {
                    mod.plan_name = it
                }
                    this[1].text.isFine {
                        mod.policy_number = it
                    }
                    this[2].text.isFine {
                        mod.insured_declared_value = it
                    }
                    this[3].text.isFine {
                        mod.type_of_cover = it
                    }
                    this[4].text.isFine {
                        mod.policy_holders_name = it
                    }
                    this[5].text.isFine {
                        mod.policy_start_date = it
                    }
                    this[6].text.isFine {
                        mod.premium_amount = it
                    }
                    this[7].text.isFine {
                        mod.renewal_date = it
                    }
                    this[8].text.isFine {
                        mod.company_helpdesk_number = it
                    }
                    this[9].text.isFine {
                        mod.nominee_name = it
                    }
                    this[10].text.isFine {
                        mod.relationship = it
                    }
                    this[11].text.isFine {
                        mod.allocation_percent = it
                    }
                    this[12].text.isFine {
                        mod.nominee_name2_ = it
                    }
                    this[13].text.isFine {
                        mod.relationship2 = it
                    }
                    this[14].text.isFine {
                        mod.allocation_percent2 = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}
            AppData.LOAN ->{
                val mod = LoanModel()
                try {                    this[0].text.isFine {
                    mod.bank_name = it
                }
                    this[1].text.isFine {
                        mod.loan_account_number = it
                    }
                    this[2].text.isFine {
                        mod.loan_amount = it
                    }
                    this[3].text.isFine {
                        mod.asset_name = it
                    }
                    this[4].text.isFine {
                        mod.asset_type = it
                    }
                    this[5].text.isFine {
                        mod.loan_start_date = it
                    }
                    this[6].text.isFine {
                        mod.loan_tenure = it
                    }
                    this[7].text.isFine {
                        mod.emi_amount = it
                    }
                    this[8].text.isFine {
                        mod.roi = it
                    }
                    this[9].text.isFine {
                        mod.ecs_bank = it
                    }
                    this[10].text.isFine {
                        mod.insurance_protect_loan = it
                    }
                    this[11].text.isFine {
                        mod.current_outstanding = it
                    }
                } catch (e: IndexOutOfBoundsException) {
                    Log.e(TAG, "mapToEntity: Index out of bound")
                } finally {
                    return mod
                }}

            else -> return null
        }
    }

    @Throws(ClassCastException::class, IndexOutOfBoundsException::class)
    fun Any.mapToList(category: Int) : List<EditDetailsModel> = when (category){
        AppData.FIXED_DEPOSIT -> {
            val list = AppData.getEditDetailsTemplate(category)
            val fd = this as FixedDeposit
            fd.bank_name.isFine {
                list[0].text = it
            }
            fd.branch_addr.isFine {
                list[1].text = it
            }
            fd.deposit_holders_name.isFine {
                list[2].text = it
            }
            fd.amount_invested.isFine {
                list[3].text = it
            }
            fd.investment_date.isFine {
                list[4].text = it
            }
            fd.investment_duration.isFine {
                list[5].text = it
            }
            fd.rate_of_interest.isFine {
                list[6].text = it
            }
            fd.maturity_date.isFine {
                list[7].text = it
            }
            fd.maturity_amount.isFine {
                list[8].text = it
            }
            fd.nominee_name.isFine {
                list[9].text = it
            }
            fd.relationship.isFine {
                list[10].text = it
            }
            fd.allocation.isFine {
                list[11].text = it
            }
            fd.nominee_name2.isFine {
                list[12].text = it
            }
            fd.relationship2.isFine {
                list[13].text = it
            }
            fd.allocation2.isFine {
                list[14].text = it
            }
            list
        }
        AppData.BANK_ACCOUNT -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as BankModel
            mod.bank_name.isFine {
                list[0].text = it
            }
            mod.branch_address.isFine {
                list[1].text = it
            }
            mod.account_holder_name.isFine {
                list[2].text = it
            }
            mod.last_4_digit_of_account_number.isFine {
                list[3].text = it
            }
            mod.account_type.isFine {
                list[4].text = it
            }
            mod.nominee_name.isFine {
                list[5].text = it
            }
            mod.relationship.isFine {
                list[6].text = it
            }
            mod.allocation_percent.isFine {
                list[7].text = it
            }
            mod.nominee_name2_.isFine {
                list[8].text = it
            }
            mod.relationship2.isFine {
                list[9].text = it
            }
            mod.allocation_percent2.isFine {
                list[10].text = it
            }
            list
        }
        AppData.MUTUAL_FUND -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as MutualModel
            mod.scheme_name.isFine {
                list[0].text = it
            }
            mod.purchase_amount.isFine {
                list[1].text = it
            }
            mod.purchase_nav.isFine {
                list[2].text = it
            }
            mod.current_nav.isFine {
                list[3].text = it
            }
            mod.current_amount.isFine {
                list[4].text = it
            }
            mod.div_amount.isFine {
                list[5].text = it
            }
            mod.gain_short_term.isFine {
                list[6].text = it
            }
            mod.gain_long_term.isFine {
                list[7].text = it
            }
            mod.return_absolute.isFine {
                list[8].text = it
            }
            mod.return_cagr.isFine {
                list[9].text = it
            }
            mod.nominee_name.isFine {
                list[10].text = it
            }
            mod.relationship.isFine {
                list[11].text = it
            }
            mod.allocation_percent.isFine {
                list[12].text = it
            }
            mod.nominee_name2_.isFine {
                list[13].text = it
            }
            mod.relationship2.isFine {
                list[14].text = it
            }
            mod.allocation_percent2.isFine {
                list[15].text = it
            }
            list
        }
        AppData.LOCKER -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as LockerModel
            mod.bank_name.isFine {
                list[0].text = it
            }
            mod.bank_address.isFine {
                list[1].text = it
            }
            mod.locker_number.isFine {
                list[2].text = it
            }
            mod.locker_holder_name.isFine {
                list[3].text = it
            }
            mod.nominee_name.isFine {
                list[4].text = it
            }
            mod.relationship.isFine {
                list[5].text = it
            }
            mod.allocation_percent.isFine {
                list[6].text = it
            }
            mod.nominee_name2_.isFine {
                list[7].text = it
            }
            mod.relationship2.isFine {
                list[8].text = it
            }
            mod.allocation_percent2.isFine {
                list[9].text = it
            }
            list
        }
        AppData.INDIAN_EQUITY -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as IndianEquityModel
            mod.broker_name.isFine {
                list[0].text = it
            }
            mod.account_number.isFine {
                list[1].text = it
            }
            mod.instrument_name.isFine {
                list[2].text = it
            }
            mod.qty.isFine {
                list[3].text = it
            }
            mod.purchase_price.isFine {
                list[4].text = it
            }
            mod.purchase_value.isFine {
                list[5].text = it
            }
            mod.market_price.isFine {
                list[6].text = it
            }
            mod.market_value.isFine {
                list[7].text = it
            }
            mod.nominee_name.isFine {
                list[8].text = it
            }
            mod.relationship.isFine {
                list[9].text = it
            }
            mod.allocation_percent.isFine {
                list[10].text = it
            }
            mod.nominee_name2_.isFine {
                list[11].text = it
            }
            mod.relationship2.isFine {
                list[12].text = it
            }
            mod.allocation_percent2.isFine {
                list[13].text = it
            }
            list
        }
        AppData.US_EQUITY -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as UsEquityModel
            mod.broker_name.isFine {
                list[0].text = it
            }
            mod.account_number.isFine {
                list[1].text = it
            }
            mod.instrument_name.isFine {
                list[2].text = it
            }
            mod.qty.isFine {
                list[3].text = it
            }
            mod.purchase_price.isFine {
                list[4].text = it
            }
            mod.purchase_value.isFine {
                list[5].text = it
            }
            mod.market_price.isFine {
                list[6].text = it
            }
            mod.market_value.isFine {
                list[7].text = it
            }
            mod.nominee_name.isFine {
                list[8].text = it
            }
            mod.relationship.isFine {
                list[9].text = it
            }
            mod.allocation_percent.isFine {
                list[10].text = it
            }
            mod.nominee_name2_.isFine {
                list[11].text = it
            }
            mod.relationship2.isFine {
                list[12].text = it
            }
            mod.allocation_percent2.isFine {
                list[13].text = it
            }
            list
        }
        AppData.CRYPTO -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as CryptoModel
            mod.broker_name.isFine {
                list[0].text = it
            }
            mod.account_number.isFine {
                list[1].text = it
            }
            mod.instrument_name.isFine {
                list[2].text = it
            }
            mod.qty.isFine {
                list[3].text = it
            }
            mod.purchase_price.isFine {
                list[4].text = it
            }
            mod.purchase_value.isFine {
                list[5].text = it
            }
            mod.market_price.isFine {
                list[6].text = it
            }
            mod.market_value.isFine {
                list[7].text = it
            }
            mod.nominee_name.isFine {
                list[8].text = it
            }
            mod.relationship.isFine {
                list[9].text = it
            }
            mod.allocation_percent.isFine {
                list[10].text = it
            }
            mod.nominee_name2_.isFine {
                list[11].text = it
            }
            mod.relationship2.isFine {
                list[12].text = it
            }
            mod.allocation_percent2.isFine {
                list[13].text = it
            }
            list
        }
        AppData.BONDS -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as BondsModel
            mod.broker_name.isFine {
                list[0].text = it
            }
            mod.account_number.isFine {
                list[1].text = it
            }
            mod.instrument_name.isFine {
                list[2].text = it
            }
            mod.qty.isFine {
                list[3].text = it
            }
            mod.purchase_price.isFine {
                list[4].text = it
            }
            mod.purchase_value.isFine {
                list[5].text = it
            }
            mod.market_price.isFine {
                list[6].text = it
            }
            mod.market_value.isFine {
                list[7].text = it
            }
            mod.nominee_name.isFine {
                list[8].text = it
            }
            mod.relationship.isFine {
                list[9].text = it
            }
            mod.allocation_percent.isFine {
                list[10].text = it
            }
            mod.nominee_name2_.isFine {
                list[11].text = it
            }
            mod.relationship2.isFine {
                list[12].text = it
            }
            mod.allocation_percent2.isFine {
                list[13].text = it
            }
            list
        }
        AppData.OFF_MARKET -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as OffMarketModel
            mod.broker_name.isFine {
                list[0].text = it
            }
            mod.account_number.isFine {
                list[1].text = it
            }
            mod.instrument_name.isFine {
                list[2].text = it
            }
            mod.qty.isFine {
                list[3].text = it
            }
            mod.purchase_price.isFine {
                list[4].text = it
            }
            mod.purchase_value.isFine {
                list[5].text = it
            }
            mod.market_price.isFine {
                list[6].text = it
            }
            mod.market_value.isFine {
                list[7].text = it
            }
            mod.nominee_name.isFine {
                list[8].text = it
            }
            mod.relationship.isFine {
                list[9].text = it
            }
            mod.allocation_percent.isFine {
                list[10].text = it
            }
            mod.nominee_name2_.isFine {
                list[11].text = it
            }
            mod.relationship2.isFine {
                list[12].text = it
            }
            mod.allocation_percent2.isFine {
                list[13].text = it
            }
            list
        }
        AppData.AIF -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as AifModel
            mod.amc_name.isFine {
                list[0].text = it
            }
            mod.pms_aif_name.isFine {
                list[1].text = it
            }
            mod.invested_value.isFine {
                list[2].text = it
            }
            mod.current_amount.isFine {
                list[3].text = it
            }
            mod.account_number.isFine {
                list[4].text = it
            }
            mod.invested_date.isFine {
                list[5].text = it
            }
            mod.return_absolute.isFine {
                list[6].text = it
            }
            mod.return_cagr.isFine {
                list[7].text = it
            }
            mod.nominee_name.isFine {
                list[8].text = it
            }
            mod.relationship.isFine {
                list[9].text = it
            }
            mod.allocation_percent.isFine {
                list[10].text = it
            }
            mod.nominee_name2_.isFine {
                list[11].text = it
            }
            mod.relationship2.isFine {
                list[12].text = it
            }
            mod.allocation_percent2.isFine {
                list[13].text = it
            }
            list
        }
        AppData.PMS -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as PmsModel
            mod.amc_name.isFine {
                list[0].text = it
            }
            mod.pms_aif_name.isFine {
                list[1].text = it
            }
            mod.invested_value.isFine {
                list[2].text = it
            }
            mod.current_amount.isFine {
                list[3].text = it
            }
            mod.account_number.isFine {
                list[4].text = it
            }
            mod.invested_date.isFine {
                list[5].text = it
            }
            mod.return_absolute.isFine {
                list[6].text = it
            }
            mod.return_cagr.isFine {
                list[7].text = it
            }
            mod.nominee_name.isFine {
                list[8].text = it
            }
            mod.relationship.isFine {
                list[9].text = it
            }
            mod.allocation_percent.isFine {
                list[10].text = it
            }
            mod.nominee_name2_.isFine {
                list[11].text = it
            }
            mod.relationship2.isFine {
                list[12].text = it
            }
            mod.allocation_percent2.isFine {
                list[13].text = it
            }
            list
        }
        AppData.EPF -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as EpfModel
            mod.epf_no.isFine {
                list[0].text = it
            }
            mod.balance.isFine {
                list[1].text = it
            }
            mod.nominee_name.isFine {
                list[2].text = it
            }
            mod.relationship.isFine {
                list[3].text = it
            }
            mod.allocation_percent.isFine {
                list[4].text = it
            }
            mod.nominee_name2_.isFine {
                list[5].text = it
            }
            mod.relationship2.isFine {
                list[6].text = it
            }
            mod.allocation_percent2.isFine {
                list[7].text = it
            }
            list
        }
        AppData.PPF -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as PpfModel
            mod.bank_name.isFine {
                list[0].text = it
            }
            mod.branch_address.isFine {
                list[1].text = it
            }
            mod.ppf_account_no.isFine {
                list[2].text = it
            }
            mod.balance.isFine {
                list[3].text = it
            }
            mod.nominee_name.isFine {
                list[4].text = it
            }
            mod.relationship.isFine {
                list[5].text = it
            }
            mod.allocation_percent.isFine {
                list[6].text = it
            }
            mod.nominee_name2_.isFine {
                list[7].text = it
            }
            mod.relationship2.isFine {
                list[8].text = it
            }
            mod.allocation_percent2.isFine {
                list[9].text = it
            }
            list
        }
        AppData.REAL_ESTATE -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as RealEstateModel
            mod.property_name.isFine {
                list[0].text = it
            }
            mod.invested_value.isFine {
                list[1].text = it
            }
            mod.investment_date.isFine {
                list[2].text = it
            }
            mod.current_value.isFine {
                list[3].text = it
            }
            mod.property_type.isFine {
                list[4].text = it
            }
            mod.property_size.isFine {
                list[5].text = it
            }
            mod.location_pincode.isFine {
                list[6].text = it
            }
            mod.nominee_name.isFine {
                list[7].text = it
            }
            mod.relationship.isFine {
                list[8].text = it
            }
            mod.allocation_percent.isFine {
                list[9].text = it
            }
            mod.nominee_name2_.isFine {
                list[10].text = it
            }
            mod.relationship2.isFine {
                list[11].text = it
            }
            mod.allocation_percent2.isFine {
                list[12].text = it
            }
            list
        }
        AppData.NPS_TIER_1 -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as NpsTierModel
            mod.pran.isFine {
                list[0].text = it
            }
            mod._current_balance.isFine {
                list[1].text = it
            }
            mod.type_of_account_.isFine {
                list[2].text = it
            }
            mod.nominee_details.isFine {
                list[3].text = it
            }
            mod.bank_details.isFine {
                list[4].text = it
            }
            mod.nominee_name.isFine {
                list[5].text = it
            }
            mod.relationship.isFine {
                list[6].text = it
            }
            mod.allocation_percent.isFine {
                list[7].text = it
            }
            mod.nominee_name2_.isFine {
                list[8].text = it
            }
            mod.relationship2.isFine {
                list[9].text = it
            }
            mod.allocation_percent2.isFine {
                list[10].text = it
            }
            list
        }
        AppData.OTHERS -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as OthersModel
            mod.instrument_name.isFine {
                list[0].text = it
            }
            mod.purchase_value.isFine {
                list[1].text = it
            }
            mod.current_current.isFine {
                list[2].text = it
            }
            mod.purchase_date.isFine {
                list[3].text = it
            }
            mod.no_of_units.isFine {
                list[4].text = it
            }
            mod.nominee_name.isFine {
                list[5].text = it
            }
            mod.relationship.isFine {
                list[6].text = it
            }
            mod.allocation_percent.isFine {
                list[7].text = it
            }
            mod.nominee_name2_.isFine {
                list[8].text = it
            }
            mod.relationship2.isFine {
                list[9].text = it
            }
            mod.allocation_percent2.isFine {
                list[10].text = it
            }
            list
        }
        AppData.LIFE_INSURANCE -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as LifeInsuranceModel
            mod.plan_name.isFine {
                list[0].text = it
            }
            mod.policy_number.isFine {
                list[1].text = it
            }
            mod.policy_holders_name.isFine {
                list[2].text = it
            }
            mod.sum_assured_.isFine {
                list[3].text = it
            }
            mod.policy_start_date.isFine {
                list[4].text = it
            }
            mod.policy_maturity_date.isFine {
                list[5].text = it
            }
            mod.policy_term_years.isFine {
                list[6].text = it
            }
            mod.premium_payment_term_years.isFine {
                list[7].text = it
            }
            mod.premium_amount.isFine {
                list[8].text = it
            }
            mod.premium_payment_frequency_.isFine {
                list[9].text = it
            }
            mod.renewal_date.isFine {
                list[10].text = it
            }
            mod.nominee_name.isFine {
                list[11].text = it
            }
            mod.relationship.isFine {
                list[12].text = it
            }
            mod.allocation_percent.isFine {
                list[13].text = it
            }
            mod.nominee_name2_.isFine {
                list[14].text = it
            }
            mod.relationship2.isFine {
                list[15].text = it
            }
            mod.allocation_percent2.isFine {
                list[16].text = it
            }
            list
        }
        AppData.HEALTH_INSURANCE -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as HealthInsuranceModel
            mod.plan_name.isFine {
                list[0].text = it
            }
            mod.policy_number.isFine {
                list[1].text = it
            }
            mod.policy_holders_name.isFine {
                list[2].text = it
            }
            mod.family_member_covered.isFine {
                list[3].text = it
            }
            mod.sum_assured_.isFine {
                list[4].text = it
            }
            mod.policy_start_date.isFine {
                list[5].text = it
            }
            mod.renewal_date.isFine {
                list[6].text = it
            }
            mod.premium_amount.isFine {
                list[7].text = it
            }
            mod.tpa_contact_number.isFine {
                list[8].text = it
            }
            mod.company_help_desk_number.isFine {
                list[9].text = it
            }
            mod.nominee_name.isFine {
                list[10].text = it
            }
            mod.relationship.isFine {
                list[11].text = it
            }
            mod.allocation_percent.isFine {
                list[12].text = it
            }
            mod.nominee_name2_.isFine {
                list[13].text = it
            }
            mod.relationship2.isFine {
                list[14].text = it
            }
            mod.allocation_percent2.isFine {
                list[15].text = it
            }
            list
        }
        AppData.MOTOR_INSURANCE -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as MotorInsuranceModel
            mod.plan_name.isFine {
                list[0].text = it
            }
            mod.policy_number.isFine {
                list[1].text = it
            }
            mod.insured_declared_value.isFine {
                list[2].text = it
            }
            mod.type_of_cover.isFine {
                list[3].text = it
            }
            mod.policy_holders_name.isFine {
                list[4].text = it
            }
            mod.policy_start_date.isFine {
                list[5].text = it
            }
            mod.premium_amount.isFine {
                list[6].text = it
            }
            mod.renewal_date.isFine {
                list[7].text = it
            }
            mod.company_helpdesk_number.isFine {
                list[8].text = it
            }
            mod.nominee_name.isFine {
                list[9].text = it
            }
            mod.relationship.isFine {
                list[10].text = it
            }
            mod.allocation_percent.isFine {
                list[11].text = it
            }
            mod.nominee_name2_.isFine {
                list[12].text = it
            }
            mod.relationship2.isFine {
                list[13].text = it
            }
            mod.allocation_percent2.isFine {
                list[14].text = it
            }
            list
        }
        AppData.LOAN -> {
            val list = AppData.getEditDetailsTemplate(category)
            val mod = this as LoanModel
            mod.bank_name.isFine {
                list[0].text = it
            }
            mod.loan_account_number.isFine {
                list[1].text = it
            }
            mod.loan_amount.isFine {
                list[2].text = it
            }
            mod.asset_name.isFine {
                list[3].text = it
            }
            mod.asset_type.isFine {
                list[4].text = it
            }
            mod.loan_start_date.isFine {
                list[5].text = it
            }
            mod.loan_tenure.isFine {
                list[6].text = it
            }
            mod.emi_amount.isFine {
                list[7].text = it
            }
            mod.roi.isFine {
                list[8].text = it
            }
            mod.ecs_bank.isFine {
                list[9].text = it
            }
            mod.insurance_protect_loan.isFine {
                list[10].text = it
            }
            mod.current_outstanding.isFine {
                list[11].text = it
            }
            list
        }

        else -> listOf()
    }

    private fun String.isFine(result : (text : String) -> Unit){
        if (this.isNotEmpty()) result(this)
    }

}