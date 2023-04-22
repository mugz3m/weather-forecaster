package ru.mugz3m.weatherforecaster.ioc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.mugz3m.weatherforecaster.data.location.repository.LocationRepository
import ru.mugz3m.weatherforecaster.data.weather.repository.WeatherForecastRepository
import ru.mugz3m.weatherforecaster.ui.stateholders.LocationViewModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class ViewModelFactory(
    private val weatherForecastRepository: WeatherForecastRepository,
    private val locationRepository: LocationRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        WeatherViewModel::class.java -> WeatherViewModel(weatherForecastRepository, locationRepository)
        LocationViewModel::class.java -> LocationViewModel(locationRepository)
        else -> throw IllegalArgumentException("${modelClass.simpleName} cannot be provided.")
    } as T
}
