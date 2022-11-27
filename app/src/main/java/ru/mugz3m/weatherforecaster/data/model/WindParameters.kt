package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class WindParameters(
    @SerializedName("speed") val windSpeed: Double,
    @SerializedName("gust") val windGust: Double?,
    @SerializedName("deg") val windDirection: Int
)
