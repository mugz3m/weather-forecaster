package ru.mugz3m.weatherforecaster.data.weather.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.weather.datasource.OpenMeteoDataSource
import ru.mugz3m.weatherforecaster.data.weather.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.weather.model.WeekWeatherForecast

class WeatherForecastRepository(
    private val dataSource: OpenMeteoDataSource
) {
    private val _currentWeatherForecast = MutableLiveData<Result<CurrentWeatherForecast>>()
    val currentWeatherForecast: LiveData<Result<CurrentWeatherForecast>> = _currentWeatherForecast

    private val _weekWeatherForecast = MutableLiveData<Result<WeekWeatherForecast>>()
    val weekWeatherForecast: LiveData<Result<WeekWeatherForecast>> = _weekWeatherForecast

    suspend fun updateCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        showProgress: MutableLiveData<Boolean>
    ) {
        withContext(Dispatchers.IO) {
            dataSource.getCurrentWeatherForecast(latitude, longitude, _currentWeatherForecast, showProgress)
        }
    }

    suspend fun updateWeekWeatherForecast(latitude: Double, longitude: Double, showProgress: MutableLiveData<Boolean>) {
        withContext(Dispatchers.IO) {
            dataSource.getWeekWeatherForecast(latitude, longitude, _weekWeatherForecast, showProgress)
        }
    }
}
