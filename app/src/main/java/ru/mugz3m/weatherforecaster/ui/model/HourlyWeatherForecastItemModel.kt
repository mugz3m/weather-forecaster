package ru.mugz3m.weatherforecaster.ui.model

data class HourlyWeatherForecastItemModel(
    val timeOfTheForecastedData: Long,
    val temperature: Double,
    val weatherIconId: String
)
