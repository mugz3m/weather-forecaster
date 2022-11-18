package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class HourlyWeatherForecast(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_offset") val timezoneOffset: Long,
    @SerializedName("hourly") val hourlyWeatherList: List<Hourly>
)

data class Hourly(
    @SerializedName("dt") val timeOfTheForecastedData: Long,
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
    @SerializedName("pop") val probabilityOfPrecipitation: Double,
    @SerializedName("weather") val weatherConditions: WeatherConditionsDataClass
)
