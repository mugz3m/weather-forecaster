package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class CloudsParameters(
    @SerializedName("all") val cloudiness: Int
)
