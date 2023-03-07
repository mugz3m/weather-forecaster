package ru.mugz3m.weatherforecaster.data.weather.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.weather.datasource.OpenWeatherOneCallDataSource
import ru.mugz3m.weatherforecaster.data.weather.model.CurrentWeatherForecast

class CurrentWeatherForecastRepository(
    private val dataSource: OpenWeatherOneCallDataSource
) {
    private val _currentWeatherForecast = MutableLiveData<CurrentWeatherForecast>()
    val currentWeatherForecast: LiveData<CurrentWeatherForecast> =
        _currentWeatherForecast

    @MainThread
    suspend fun updateCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
        language: String,
        showProgress: MutableLiveData<Boolean>
    ) {
        withContext(Dispatchers.IO) {
            dataSource.getCurrentWeatherForecast(
                latitude,
                longitude,
                apiKey,
                units,
                language,
                _currentWeatherForecast,
                showProgress
            )
        }
    }
}
