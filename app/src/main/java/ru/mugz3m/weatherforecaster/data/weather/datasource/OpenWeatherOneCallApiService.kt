package ru.mugz3m.weatherforecaster.data.weather.datasource

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mugz3m.weatherforecaster.data.weather.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.weather.model.FiveDayWeatherForecast

interface OpenWeatherOneCallApiService {
    @GET("data/2.5/weather")
    fun getCurrentWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Call<CurrentWeatherForecast>

    @GET("data/2.5/forecast")
    fun getFiveDayWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Call<FiveDayWeatherForecast>
}
