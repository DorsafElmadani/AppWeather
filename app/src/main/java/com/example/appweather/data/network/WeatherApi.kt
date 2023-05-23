package com.example.appweather.data.network

import com.example.appweather.data.model.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface  WeatherApi{
    @GET("weather")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") apiKey: String
    ): WeatherModel
}