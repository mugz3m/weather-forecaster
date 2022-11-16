package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class WeatherConditionsDataClass(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val weatherParameters: String,
    @SerializedName("description") val weatherCondition: String,
    @SerializedName("icon") val weatherIconId: String
)
