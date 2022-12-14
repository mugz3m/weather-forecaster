package ru.mugz3m.weatherforecaster.ui.stateholders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.FiveDayWeatherForecast
import ru.mugz3m.weatherforecaster.data.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.repository.FiveDayWeatherForecastRepository

class WeatherViewModel(
    private val currentWeatherForecastRepository: CurrentWeatherForecastRepository,
    private val fiveDayWeatherForecastRepository: FiveDayWeatherForecastRepository
) : ViewModel() {
    private val _currentWeatherForecastLiveData = MutableLiveData<CurrentWeatherForecast>()
    val currentWeatherForecastLiveData: LiveData<CurrentWeatherForecast> =
        _currentWeatherForecastLiveData

    private val _fiveDayWeatherForecastMutableLiveData = MutableLiveData<FiveDayWeatherForecast>()
    val fiveDayWeatherForecastMutableLiveData: LiveData<FiveDayWeatherForecast> =
        _fiveDayWeatherForecastMutableLiveData

    val currentWeatherForecast = currentWeatherForecastRepository.currentWeatherForecast
    val fiveDayWeatherForecast = fiveDayWeatherForecastRepository.fiveDayWeatherForecast

    init {
        updateAllWeatherForecasts()
    }

    fun updateAllWeatherForecasts() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                listOf(launch {
                    currentWeatherForecastRepository.updateCurrentWeatherForecast(
                        55.75,
                        37.61,
                        "9f3f82c0c44e6c4edfbdc8061b5954a5",
                        "metric",
                        "en"
                    )
                }, launch {
                    fiveDayWeatherForecastRepository.updateFiveDayWeatherForecast(
                        55.75,
                        37.61,
                        "9f3f82c0c44e6c4edfbdc8061b5954a5",
                        "metric",
                        "en"
                    )
                }).joinAll()
            }
        }
    }
}
