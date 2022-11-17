package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class CurrentWeatherForecastResponseBody(
    var responseBody: CurrentWeatherForecast? = null,
    var responseCode: Int? = null,
    var error: Throwable? = null
)

data class CurrentWeatherForecast(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_offset") val timezoneOffset: Long,
    @SerializedName("current") val current: Current
)

data class Current(
    @SerializedName("dt") val currentTime: Long,
    @SerializedName("sunrise") val sunriseTime: Long,
    @SerializedName("sunset") val sunsetTime: Long,
    @SerializedName("temp") val temperature: Double,
    @SerializedName("feels_like") val feelsLikeTemperature: Double,
    @SerializedName("pressure") val atmosphericPressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dewPointTemperature: Double,
    @SerializedName("uvi") val uvIndex: Double,
    @SerializedName("clouds") val cloudiness: Int,
    @SerializedName("visibility") val averageVisibility: Int,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_gust") val windGust: Double?,
    @SerializedName("wind_deg") val windDirection: Int,
    @SerializedName("weather") val weatherConditions: WeatherConditionsDataClass
)
