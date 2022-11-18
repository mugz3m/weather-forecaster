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
import ru.mugz3m.weatherforecaster.data.model.DailyWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.HourlyWeatherForecast
import ru.mugz3m.weatherforecaster.data.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.repository.DailyWeatherForecastsRepository
import ru.mugz3m.weatherforecaster.data.repository.HourlyWeatherForecastsRepository

class WeatherViewModel(
    private val currentWeatherForecastRepository: CurrentWeatherForecastRepository,
    private val hourlyWeatherForecastsRepository: HourlyWeatherForecastsRepository,
    private val dailyWeatherForecastsRepository: DailyWeatherForecastsRepository
) : ViewModel() {
    private val _currentWeatherForecastLiveData = MutableLiveData<CurrentWeatherForecast>()
    val currentWeatherForecastLiveData: LiveData<CurrentWeatherForecast> =
        _currentWeatherForecastLiveData

    private val _hourlyWeatherForecastLiveData = MutableLiveData<HourlyWeatherForecast>()
    val hourlyWeatherForecastsLiveData: LiveData<HourlyWeatherForecast> =
        _hourlyWeatherForecastLiveData

    private val _dailyWeatherForecastLiveData = MutableLiveData<DailyWeatherForecast>()
    val dailyWeatherForecastLiveData: LiveData<DailyWeatherForecast> = _dailyWeatherForecastLiveData

    val currentWeatherForecast = currentWeatherForecastRepository.currentWeatherForecast
    val hourlyWeatherForecast = hourlyWeatherForecastsRepository.hourlyWeatherForecast
    val dailyWeatherForecast = dailyWeatherForecastsRepository.dailyWeatherForecast

    init {
        updateAllWeatherForecasts()
    }

    fun updateAllWeatherForecasts() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                listOf(launch {
                    currentWeatherForecastRepository.updateCurrentWeatherForecast(
                        33.44,
                        -94.04,
                        "current",
                        "9f3f82c0c44e6c4edfbdc8061b5954a5",
                        "metric",
                        "en"
                    )
                }, launch {
                    hourlyWeatherForecastsRepository.updateHourlyWeatherForecast(
                        33.44,
                        -94.04,
                        "hourly",
                        "9f3f82c0c44e6c4edfbdc8061b5954a5",
                        "metric",
                        "english"
                    )
                }, launch {
                    dailyWeatherForecastsRepository.updateDailyWeatherForecast(
                        33.44,
                        -94.04,
                        "daily",
                        "9f3f82c0c44e6c4edfbdc8061b5954a5",
                        "metric",
                        "english"
                    )
                }).joinAll()
            }
        }
    }
}
