package com.example.greekcityevents.api

import com.example.greekcityevents.model.Event
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/") // Update this URL to your API endpoint
    fun getEvents(
        @Query("city") city: String?,
        @Query("category") category: String?
    ): Call<List<Event>>

    @GET("events/Category/{category}")
    fun getEventsByCategory(
        @Path("category") category: String
    ): Call<List<Event>>

    @GET("events/{city}")
    fun getEventsByCity(
        @Path("city") city: String
    ): Call<List<Event>>
}



object RetrofitClient {
    private const val BASE_URL = "https://events-app-med4.onrender.com/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
