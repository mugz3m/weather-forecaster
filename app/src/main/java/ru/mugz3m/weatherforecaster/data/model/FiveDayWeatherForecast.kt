package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class FiveDayWeatherForecast(
    @SerializedName("cod") val responseCode: Int,
    @SerializedName("message") val responseMessage: String,
    @SerializedName("cnt") val numberOfTimestamps: Int,
    @SerializedName("list") val forecastsList: List<FiveDayWeatherForecastItem>,
    @SerializedName("city") val cityForWhichForecastMade: CityForWhichForecastMade
)

data class FiveDayWeatherForecastItem(
    @SerializedName("dt") val timeOfTheForecastedData: Long,
    @SerializedName("main") val weatherParameters: WeatherParameters,
    @SerializedName("weather") val weatherConditions: List<WeatherConditions>,
    @SerializedName("clouds") val clouds: CloudsParameters,
    @SerializedName("wind") val wind: WindParameters
)

data class CityForWhichForecastMade(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val forecastCoordinates: ForecastCoordinates,
    @SerializedName("country") val country: String,
    @SerializedName("population") val population: Int,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("sunset") val sunsetTime: Long,
    @SerializedName("moonrise") val moonriseTime: Long
)


