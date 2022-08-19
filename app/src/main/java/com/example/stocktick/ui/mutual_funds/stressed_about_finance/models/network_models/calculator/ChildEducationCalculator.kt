package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.calculator

import com.squareup.moshi.Json

data class ChildEducationCalculator(
    @Json( name ="current_edu_cost"           ) var currentEduCost           : Double? = 0.0,
    @Json( name ="inflation_rate"             ) var inflationRate            : Double?    = 0.0,
    @Json( name ="current_age"                ) var currentAge               : Double?    = 0.0,
    @Json( name ="corpus_age"                 ) var corpusAge                : Double?    = 0.0,
    @Json( name ="expected_investment_return" ) var expectedInvestmentReturn : Double? = 0.0,
    @Json( name ="future_edu_cost"            ) var futureEduCost            : Double? = 0.0,
    @Json( name ="monthly_investment_reqd"    ) var monthlyInvestmentReqd    : Double?    = 0.0

)