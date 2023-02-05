package ru.mugz3m.weatherforecaster.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mugz3m.weatherforecaster.data.location.repository.LocationRepository
import ru.mugz3m.weatherforecaster.data.weather.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.weather.repository.FiveDayWeatherForecastRepository
import ru.mugz3m.weatherforecaster.ui.stateholders.LocationViewModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class ViewModelFactory(
    private val currentWeatherForecastRepository: CurrentWeatherForecastRepository,
    private val fiveDayWeatherForecastRepository: FiveDayWeatherForecastRepository,
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        WeatherViewModel::class.java -> WeatherViewModel(
            currentWeatherForecastRepository,
            fiveDayWeatherForecastRepository,
            locationRepository
        )

        LocationViewModel::class.java -> LocationViewModel(locationRepository)
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
