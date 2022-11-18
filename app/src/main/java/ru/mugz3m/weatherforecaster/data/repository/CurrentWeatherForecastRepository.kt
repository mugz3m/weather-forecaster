package ru.mugz3m.weatherforecaster.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.datasource.OpenWeatherOneCallDataSource
import ru.mugz3m.weatherforecaster.data.model.CurrentWeatherForecast

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
        language: String
    ) {
        withContext(Dispatchers.IO) {
            dataSource.getCurrentWeatherForecast(
                latitude,
                longitude,
                apiKey,
                units,
                language,
                _currentWeatherForecast
            )
        }
    }
}
