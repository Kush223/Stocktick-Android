package com.example.stocktick.ui.mutual_funds.discover_mutual_funds.models.network_models

import com.squareup.moshi.Json

data class GetDetailsModel(
    @Json(name ="id"           ) var id          : Int?    = null,
    @Json(name ="created_by"   ) var createdBy   : String? = null,
    @Json(name ="created_at"   ) var createdAt   : String? = null,
    @Json(name ="modified_by"  ) var modifiedBy  : String? = null,
    @Json(name ="modified_at"  ) var modifiedAt  : String? = null,
    @Json(name ="fund_name"    ) var fundName    : String? = null,
    @Json(name ="long_desc"    ) var longDesc    : String? = null,
    @Json(name ="icon"         ) var icon        : String? = null,
    @Json(name ="oneday"       ) var oneday      : Int?    = null,
    @Json(name ="oneyear"      ) var oneyear     : Int?    = null,
    @Json(name ="threeyear"    ) var threeyear   : Int?    = null,
    @Json(name ="redirect_url" ) var redirectUrl : String? = null,
    @Json(name ="fetch_url"    ) var fetchUrl    : String? = null,
    @Json(name ="catg_id"      ) var catgId      : Int?    = null,
    @Json(name ="catg_name"    ) var catgName    : String? = null,
    @Json(name ="weightage"    ) var weightage   : Int?    = null,
    @Json(name ="last_sync"    ) var lastSync    : String? = null,
    @Json(name ="status"       ) var status      : String? = null,
    @Json(name ="lock_status"  ) var lockStatus  : String? = null
)

data class GetDetailsBody(
    val catg_id: String="1"
)
