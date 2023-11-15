package com.example.project2.apis

import com.example.project2.all_constants.ApiConstants.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class  ApiInstance {
    companion object {
        private val retrofit by lazy {
            val client = OkHttpClient.Builder().build()
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofit.create(PostsApi::class.java)
        }
    }
}