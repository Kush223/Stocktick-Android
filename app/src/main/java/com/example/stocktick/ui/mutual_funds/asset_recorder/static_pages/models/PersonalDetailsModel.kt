package com.example.stocktick.ui.mutual_funds.asset_recorder.static_pages.models

import com.squareup.moshi.Json

data class PersonalDetailsModel(

    @Json(name = "name"              ) var name             : String? = null,
    @Json( name ="address"           ) var address          : String? = null,
    @Json( name ="mobile"            ) var mobile           : String? = null,
    @Json( name ="email_id"          ) var emailId          : String? = null,
    @Json( name ="dob"               ) var dob              : String? = null,
    @Json( name ="blood_group"       ) var bloodGroup       : String? = null,
    @Json( name ="emergency_name"    ) var emergencyName    : String? = null,
    @Json( name ="emergency_contact" ) var emergencyContact : String? = null,
    @Json( name ="emergency_address" ) var emergencyAddress : String? = null

)
