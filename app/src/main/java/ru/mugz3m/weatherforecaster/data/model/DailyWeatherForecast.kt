package ru.mugz3m.weatherforecaster.data.model

import com.google.gson.annotations.SerializedName

data class DailyWeatherForecast(
    @SerializedName("lat") val latitude: Double,
    @SerializedName("lon") val longitude: Double,
    @SerializedName("timezone") val timezone: String,
    @SerializedName("timezone_offset") val timezoneOffset: Long,
    @SerializedName("daily") val dailyWeatherList: List<Daily>
)

data class Daily(
    @SerializedName("dt") val timeOfTheForecastedData: Long,
    @SerializedName("sunrise") val sunriseTime: Long,
    @SerializedName("sunset") val sunsetTime: Long,
    @SerializedName("moonrise") val moonriseTime: Long,
    @SerializedName("moonset") val moonsetTime: Long,
    @SerializedName("moon_phase") val moonPhase: Double,
    @SerializedName("temp") val temperaturesDuringTheDay: TemperaturesDuringTheDayDataClass,
    @SerializedName("feels_like") val feelsLikeTemperaturesDuringTheDay: FeelsLikeTemperaturesDuringTheDayDataClass,
    @SerializedName("pressure") val atmosphericPressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("dew_point") val dewPointTemperature: Double,
    @SerializedName("wind_speed") val windSpeed: Double,
    @SerializedName("wind_gust") val windGust: Double?,
    @SerializedName("wind_deg") val windDirection: Int,
    @SerializedName("clouds") val cloudiness: Int,
    @SerializedName("uvi") val uvIndex: Double,
    @SerializedName("pop") val probabilityOfPrecipitation: Double,
    @SerializedName("rain") val precipitationVolume: Int,
    @SerializedName("snow") val snowVolume: Int,
    @SerializedName("weather") val weatherConditions: WeatherConditionsDataClass
)

data class TemperaturesDuringTheDayDataClass(
    @SerializedName("morn") val morning: Double,
    @SerializedName("day") val day: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("night") val night: Double,
    @SerializedName("min") val minDaily: Double,
    @SerializedName("max") val maxDaily: Double
)

data class FeelsLikeTemperaturesDuringTheDayDataClass(
    @SerializedName("morn") val morning: Double,
    @SerializedName("day") val day: Double,
    @SerializedName("eve") val evening: Double,
    @SerializedName("night") val night: Double
)
