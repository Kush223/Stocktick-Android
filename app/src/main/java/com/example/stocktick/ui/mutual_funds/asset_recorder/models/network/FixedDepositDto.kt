package com.example.stocktick.ui.mutual_funds.asset_recorder.models.network

import com.squareup.moshi.Json

data class FixedDepositDto(
    @Json( name = "id") var id: Int? = null,
    @Json(name = "created_by") var createdBy: String? = null,
    @Json(name = "created_at") var createdAt: String? = null,
    @Json(name = "modified_by") var modifiedBy: String? = null,
    @Json(name = "modified_at") var modifiedAt: String? = null,
    @Json(name = "userId") var userId: Int? = null,
    @Json(name = "nominee1") var nominee1: String? = null,
    @Json(name = "relationship1") var relationship1: String? = null,
    @Json(name = "allocation1") var allocation1: String? = null,
    @Json(name = "nominee2") var nominee2: String? = null,
    @Json(name = "relationship2") var relationship2: String? = null,
    @Json(name = "allocation2") var allocation2: String? = null,
    @Json(name = "type") var type: String? = null,
    @Json(name = "status") var status: String? = null,
    @Json(name = "bank_name") var bankName: String? = null,
    @Json( name = "deposit_name") var depositName: String? = null,
    @Json(name = "maturity_date") var maturityDate: String? = null,
    @Json(name = "rate_interest") var rateInterest: String? = null,
    @Json(name = "branch_address") var branchAddress: String? = null,
    @Json(name = "amount_invested") var amountInvested: String? = null,
    @Json(name = "investment_date") var investmentDate: String? = null,
    @Json(name = "maturity_amount") var maturityAmount: String? = null,
    @Json(name = "investment_duration") var investmentDuration: String? = null
)
