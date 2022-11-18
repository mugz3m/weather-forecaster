package ru.mugz3m.weatherforecaster.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mugz3m.weatherforecaster.data.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.repository.FiveDayWeatherForecastRepository
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class ViewModelFactory(
    private val currentWeatherForecastRepository: CurrentWeatherForecastRepository,
    private val fiveDayWeatherForecastRepository: FiveDayWeatherForecastRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        WeatherViewModel::class.java -> WeatherViewModel(
            currentWeatherForecastRepository,
            fiveDayWeatherForecastRepository
        )
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
