package ru.mugz3m.weatherforecaster.ui.model

data class CurrentWeatherForecastModel(
    val temperature: Double,
    val feelsLikeTemperature: Double,
    val atmosphericPressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val windDirection: Int,
    val weatherCondition: String,
    val weatherIconId: String
)
