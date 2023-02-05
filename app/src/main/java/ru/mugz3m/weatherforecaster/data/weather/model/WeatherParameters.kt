package ru.mugz3m.weatherforecaster.data.weather.model

import com.google.gson.annotations.SerializedName

data class WeatherParameters(
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLikeTemperature: Double,
    @SerializedName("pressure") val atmosphericPressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_min") val minimumTemperature: Double,
    @SerializedName("temp_max") val maximumTemperature: Double
)
