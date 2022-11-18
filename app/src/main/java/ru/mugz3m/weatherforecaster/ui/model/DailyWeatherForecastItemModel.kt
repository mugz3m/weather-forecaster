package ru.mugz3m.weatherforecaster.ui.model

data class DailyWeatherForecastItemModel(
    val timeOfTheForecastedData: Long,
    val minDailyTemperature: Double,
    val maxDailyTemperature: Double,
    val weatherIconId: String
)
