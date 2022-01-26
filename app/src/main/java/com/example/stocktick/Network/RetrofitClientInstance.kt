package com.example.stocktick.Network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {
    private const val BASE_URL: String = "https://stocktickqa.herokuapp.com/"
    val getClient : JsonPlaceholderApi
        get(){
            val gson = GsonBuilder().setLenient().create()
            val httpClient = OkHttpClient.Builder().build()
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit.create(JsonPlaceholderApi::class.java)
        }
}