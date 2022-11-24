package ru.mugz3m.weatherforecaster.data.weather.model

import com.google.gson.annotations.SerializedName

data class ForecastCoordinates(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double
)
