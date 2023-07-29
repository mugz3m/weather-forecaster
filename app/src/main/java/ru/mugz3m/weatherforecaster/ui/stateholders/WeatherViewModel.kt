package ru.mugz3m.weatherforecaster.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.location.repository.LocationRepository
import ru.mugz3m.weatherforecaster.data.weather.repository.WeatherForecastRepository

class WeatherViewModel(
    private val weatherForecastRepository: WeatherForecastRepository,
    locationRepository: LocationRepository
) : ViewModel() {
    val currentWeatherForecast = weatherForecastRepository.currentWeatherForecast
    val weekWeatherForecast = weatherForecastRepository.weekWeatherForecast

    val currentLocation = locationRepository.currentLocation

    private val _showProgress = MutableLiveData<Boolean>()
    val showProgress: LiveData<Boolean> = _showProgress

    fun updateAllWeatherForecasts() {
        _showProgress.value = true
        if (currentLocation.value != null)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    listOf(
                        launch {
                            weatherForecastRepository.updateCurrentWeatherForecast(
                                latitude = currentLocation.value!!.latitude,
                                longitude = currentLocation.value!!.longitude,
                                showProgress = _showProgress
                            )
                        },
                        launch {
                            weatherForecastRepository.updateWeekWeatherForecast(
                                latitude = currentLocation.value!!.latitude,
                                longitude = currentLocation.value!!.longitude,
                                showProgress = _showProgress
                            )
                        },
                    ).joinAll()
                }
            }
    }
}
