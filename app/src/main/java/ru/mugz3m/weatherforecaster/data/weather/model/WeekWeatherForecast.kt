package ru.mugz3m.weatherforecaster.data.weather.model

import com.google.gson.annotations.SerializedName

data class WeekWeatherForecast(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("generationtime_ms") val generationTimeMillis: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Long,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerializedName("elevation") val elevation: Int,
    @SerializedName("daily_units") val dailyUnits: DailyUnits,
    @SerializedName("daily") val daily: DailyWeather,
)

data class DailyUnits(
    @SerializedName("time") val time: String,
    @SerializedName("weathercode") val weatherCode: String,
    @SerializedName("temperature_2m_max") val temperatureMax: String,
    @SerializedName("temperature_2m_min") val temperatureMin: String,
)

data class DailyWeather(
    @SerializedName("time") val time: List<String>,
    @SerializedName("weathercode") val weatherCode: List<Int>,
    @SerializedName("temperature_2m_max") val temperatureMax: List<Double>,
    @SerializedName("temperature_2m_min") val temperatureMin: List<Double>,
)
