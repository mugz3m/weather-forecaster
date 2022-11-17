package ru.mugz3m.weatherforecaster.data.datasource

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mugz3m.weatherforecaster.data.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.DailyWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.HourlyWeatherForecast

interface OpenWeatherOneCallApiService {
    @GET("data/3.0/onecall")
    suspend fun getCurrentWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Call<CurrentWeatherForecast>

    @GET("data/3.0/onecall")
    suspend fun getHourlyWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Call<HourlyWeatherForecast>

    @GET("data/3.0/onecall")
    suspend fun getDailyWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String,
        @Query("lang") language: String
    ): Call<DailyWeatherForecast>
}
