package com.example.stocktick.ui.mutual_funds.asset_recorder.static_pages.models

import com.squareup.moshi.Json

data class KeyPeopleModel(
    @Json( name = "family_doctor"    ) var familyDoctor   : String? = null,
    @Json( name = "family_doctor_c1" ) var familyDoctorC1 : String? = null,
    @Json( name = "family_doctor_c2" ) var familyDoctorC2 : String? = null,
    @Json( name = "CA"               ) var CA             : String? = null,
    @Json( name = "CA_c1"            ) var CAC1           : String? = null,
    @Json( name = "CA_c2"            ) var CAC2           : String? = null,
    @Json( name = "advocate"         ) var advocate       : String? = null,
    @Json( name = "advocate_c1"      ) var advocateC1     : String? = null,
    @Json( name = "advocate_c2"      ) var advocateC2     : String? = null,
    @Json( name = "advisor"          ) var advisor        : String? = null,
    @Json( name = "advisor_c1"       ) var advisorC1      : String? = null,
    @Json( name = "advisor_c2"       ) var advisorC2      : String? = null

)
