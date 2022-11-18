package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class WindParameters(
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_gust") val windGust: Double?,
    @SerializedName("wind_deg") val windDirection: Int
)
