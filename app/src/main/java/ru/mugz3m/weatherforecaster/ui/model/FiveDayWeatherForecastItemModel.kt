package ru.mugz3m.weatherforecaster.ui.model

data class FiveDayWeatherForecastItemModel(
    val timeOfTheForecastedData: Long,
    val temperature: Double,
    val weatherIconId: String
)
