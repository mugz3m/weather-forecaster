package ru.mugz3m.weatherforecaster.ui.model

data class CurrentWeatherForecastModel(
    val temperature: Double,
    val windSpeed: Double,
    val windDirection: Int,
    val weatherCode: Int,
)
