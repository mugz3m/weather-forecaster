package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class WeatherConditions(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val groupOfWeatherParameters: String,
    @SerializedName("description") val weatherCondition: String,
    @SerializedName("icon") val weatherIconId: String
)
