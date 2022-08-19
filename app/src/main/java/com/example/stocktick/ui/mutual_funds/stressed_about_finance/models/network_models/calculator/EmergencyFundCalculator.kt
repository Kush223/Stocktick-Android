package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.calculator

import com.squareup.moshi.Json

data class EmergencyFundCalculator(
    @Json( name = "living_expense"          ) var livingExpense         : Double? = 0.0,
    @Json( name = "emergency_rate"          ) var emergencyRate         : Double?    = 0.0,
    @Json( name = "emergency_fund"          ) var emergencyFund         : Double?    = 0.0,
    @Json( name = "investment_return"       ) var investmentReturn      : Double?    = 0.0,
    @Json( name = "emergency_fund_you_need" ) var emergencyFundYouNeed  : Double? = 0.0,
    @Json( name = "monthly_investment_reqd" ) var monthlyInvestmentReqd : Double? = 0.0

)
