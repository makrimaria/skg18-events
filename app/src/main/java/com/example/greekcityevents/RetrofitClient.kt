package com.example.greekcityevents.api

import com.example.greekcityevents.model.Event
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("events") // Update this URL to your API endpoint
    fun getEvents(
        @Query("city") city: String?,
        @Query("category") category: String?
    ): Call<List<Event>>
}

object RetrofitClient {
    private const val BASE_URL = "https://your-api-url.com/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
