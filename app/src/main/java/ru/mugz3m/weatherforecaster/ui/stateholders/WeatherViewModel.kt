package ru.mugz3m.weatherforecaster.ui.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.location.repository.LocationRepository
import ru.mugz3m.weatherforecaster.data.weather.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.weather.repository.FiveDayWeatherForecastRepository

class WeatherViewModel(
    private val currentWeatherForecastRepository: CurrentWeatherForecastRepository,
    private val fiveDayWeatherForecastRepository: FiveDayWeatherForecastRepository,
    locationRepository: LocationRepository
) : ViewModel() {
    val currentWeatherForecast = currentWeatherForecastRepository.currentWeatherForecast
    val fiveDayWeatherForecast = fiveDayWeatherForecastRepository.fiveDayWeatherForecast

    val currentLocation = locationRepository.currentLocation

    fun updateAllWeatherForecasts() {
        val latitude = currentLocation.value?.latitude
        val longitude = currentLocation.value?.longitude
        if (latitude != null && longitude != null) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    listOf(launch {
                        currentWeatherForecastRepository.updateCurrentWeatherForecast(
                            latitude = latitude,
                            longitude = longitude,
                            apiKey = "9f3f82c0c44e6c4edfbdc8061b5954a5",
                            units = "metric",
                            language = "en"
                        )
                    }, launch {
                        fiveDayWeatherForecastRepository.updateFiveDayWeatherForecast(
                            latitude = latitude,
                            longitude = longitude,
                            apiKey = "9f3f82c0c44e6c4edfbdc8061b5954a5",
                            units = "metric",
                            language = "en"
                        )
                    }).joinAll()
                }
            }
        }
    }
}
