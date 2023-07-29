package ru.mugz3m.weatherforecaster.data.weather.datasource

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import ru.mugz3m.weatherforecaster.data.weather.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.weather.model.WeekWeatherForecast

interface OpenMeteoApiService {
    @GET("forecast")
    fun getCurrentWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current_weather") currentWeather: Boolean = true,
        @Query("windspeed_unit") windSpeedUnit: String = "ms",
        @Query("timezone") timeZone: String = "auto"
    ): Call<CurrentWeatherForecast>

    @GET("forecast")
    fun getWeekWeatherForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: String = "weathercode,temperature_2m_max,temperature_2m_min",
        @Query("windspeed_unit") windSpeedUnit: String = "ms",
        @Query("timezone") timeZone: String = "auto"
    ): Call<WeekWeatherForecast>
}
