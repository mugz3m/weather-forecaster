package ru.mugz3m.weatherforecaster.ui.model

data class WeekWeatherForecastItemModel(
    val dateTime: String,
    val weatherCode: Int,
    val temperatureMax: Double,
    val temperatureMin: Double,
)
