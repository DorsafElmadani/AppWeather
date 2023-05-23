package com.example.appweather.ui.weather

import com.example.appweather.data.model.WeatherModel
import com.example.appweather.data.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val api : WeatherApi
) {
    companion object {
        private const val API_KEY = "e0e643c785518dc8f21d9a77a1b531f1"
    }
    suspend fun getWeatherData(city: String) : WeatherModel {
        return api.getWeatherData(city,API_KEY)
    }
}