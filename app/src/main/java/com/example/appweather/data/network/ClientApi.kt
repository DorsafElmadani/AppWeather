package com.example.appweather.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class ClientApi @Inject constructor() {
    companion object {
        private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    }

    fun <Api> buildApi(
    api : Class<Api>
    ): Api {
        return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(api)

    }

}
