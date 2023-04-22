package ru.mugz3m.weatherforecaster.data.weather.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherForecast(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("generationtime_ms") val generationTimeMillis: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Long,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerializedName("elevation") val elevation: Int,
    @SerializedName("current_weather") val currentWeather: CurrentWeather,
)

data class CurrentWeather(
    @SerializedName("temperature") val temperature: Double,
    @SerializedName("windspeed") val windSpeed: Double,
    @SerializedName("winddirection") val windDirection: Int,
    @SerializedName("weathercode") val weatherCode: Int,
    @SerializedName("time") val time: String,
)
