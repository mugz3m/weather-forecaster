package ru.mugz3m.weatherforecaster.data.weather.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.weather.datasource.OpenWeatherOneCallDataSource
import ru.mugz3m.weatherforecaster.data.weather.model.FiveDayWeatherForecast

class FiveDayWeatherForecastRepository(
    private val dataSource: OpenWeatherOneCallDataSource
) {
    private val _fiveDayWeatherForecast = MutableLiveData<FiveDayWeatherForecast>()
    val fiveDayWeatherForecast: LiveData<FiveDayWeatherForecast> = _fiveDayWeatherForecast

    @MainThread
    suspend fun updateFiveDayWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
        language: String
    ) {
        withContext(Dispatchers.IO) {
            dataSource.getFiveDayWeatherForecast(
                latitude,
                longitude,
                apiKey,
                units,
                language,
                _fiveDayWeatherForecast
            )
        }
    }
}
