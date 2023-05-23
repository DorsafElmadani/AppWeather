package com.example.appweather.di

import com.example.appweather.data.network.APIClient
import com.example.appweather.data.network.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
     @Provides
     fun provideWeatherApi(
          clientApi: APIClient
     ) : WeatherApi{
          return clientApi.buildApi(WeatherApi::class.java)
     }
}