package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.calculator

import com.squareup.moshi.Json

data class RetirementCalculator (
    @Json( name ="monthly_income_reqd"     ) var monthlyIncomeReqd      : Double? = 0.0,
    @Json( name ="inflation_rate"          ) var inflationRate          : Double?    = 0.0,
    @Json( name ="age"                     ) var age                    : Double?    = 0.0,
    @Json( name ="retirement_age"          ) var retirementAge          : Double?    = 0.0,
    @Json( name ="life_expectancy"         ) var lifeExpectancy         : Double?    = 0.0,
    @Json( name ="current_investment"      ) var currentInvestment      : Double?    = 0.0,
    @Json( name ="return_rate"             ) var returnRate             : Double?    = 0.0,
    @Json( name ="annual_income_retire"    ) var annualIncomeRetire     : Double?    = 0.0,
    @Json( name ="investment_appreciation" ) var investmentAppreciation : Double?    = 0.0,
    @Json( name ="corpus_deficit"          ) var corpusDeficit          : Double?    = 0.0,
    @Json( name ="lumpsum_fund_reqd"       ) var lumpsumFundReqd        : Double?    = 0.0,
    @Json( name ="monthly_investment_reqd" ) var monthlyInvestmentReqd  : Double?    = 0.0
        )