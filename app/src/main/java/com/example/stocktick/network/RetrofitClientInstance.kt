package com.example.stocktick.network

import com.example.stocktick.utility.Constant.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
        .baseUrl(BASE_URL)
        .build()


object RetrofitClientInstance {
    val retrofitService: ApiServiceInterface by lazy {
        retrofit.create(ApiServiceInterface::class.java)
    }
}