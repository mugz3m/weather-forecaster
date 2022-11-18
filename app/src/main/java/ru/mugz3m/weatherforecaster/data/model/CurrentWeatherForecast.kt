package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherForecast(
    @SerializedName("cod") val responseCode: Int,
    @SerializedName("coord") val forecastCoordinates: ForecastCoordinates,
    @SerializedName("weather") val weatherConditions: List<WeatherConditions>,
    @SerializedName("main") val weatherParameters: WeatherParameters,
    @SerializedName("wind") val wind: WindParameters,
    @SerializedName("clouds") val clouds: CloudsParameters,
    @SerializedName("dt") val currentTime: Long,
    @SerializedName("sys") val system: ForecastSystemData,
    @SerializedName("timezone") val timezone: Long,
)

data class ForecastSystemData(
    @SerializedName("type") val type: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("message") val message: String,
    @SerializedName("country") val country: String,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)
