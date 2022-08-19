package com.example.stocktick.ui.mutual_funds.stressed_about_finance.models.network_models.calculator

import com.squareup.moshi.Json

data class ChildMarriageCalculator(

    @Json( name = "spend"               ) var spend             : Double? = 0.0,
    @Json( name = "inflation_rate"      ) var inflationRate     : Double? = 0.0,
    @Json( name = "age"                 ) var age               : Double? = 0.0,
    @Json( name = "marriage_age"        ) var marriageAge       : Double? = 0.0,
    @Json( name = "investment_return"   ) var investmentReturn  : Double? = 0.0,
    @Json( name = "child_marriage_cost" ) var childMarriageCost : Double? = 0.0,
    @Json( name = "monthly_investment"  ) var monthlyInvestment : Double? = 0.0
)
