package com.example.stocktick.ui.mutual_funds.explore_more_mutual_funds.models

import com.squareup.moshi.Json

data class ExploreMoreMfCategoryModel(
    @Json( name ="id"             ) var id           : Int?    = 1,
    @Json( name ="created_by"     ) var createdBy    : String? = null,
    @Json( name ="created_at"     ) var createdAt    : String? = null,
    @Json( name ="modified_by"    ) var modifiedBy   : String? = null,
    @Json( name ="modified_at"    ) var modifiedAt   : String? = null,
    @Json( name ="fund_catg_name" ) var fundCatgName : String? = null,
    @Json( name ="icon"           ) var icon         : String? = null,
    @Json( name ="status"         ) var status       : String? = null,
    @Json( name ="lock_status"    ) var lockStatus   : String? = null
)
