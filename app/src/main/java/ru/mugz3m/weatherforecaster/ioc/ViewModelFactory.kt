package ru.mugz3m.weatherforecaster.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mugz3m.weatherforecaster.data.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.repository.DailyWeatherForecastsRepository
import ru.mugz3m.weatherforecaster.data.repository.HourlyWeatherForecastsRepository
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class ViewModelFactory(
    private val currentWeatherForecastRepository: CurrentWeatherForecastRepository,
    private val hourlyWeatherForecastsRepository: HourlyWeatherForecastsRepository,
    private val dailyWeatherForecastsRepository: DailyWeatherForecastsRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        WeatherViewModel::class.java -> WeatherViewModel(
            currentWeatherForecastRepository,
            hourlyWeatherForecastsRepository,
            dailyWeatherForecastsRepository
        )
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
