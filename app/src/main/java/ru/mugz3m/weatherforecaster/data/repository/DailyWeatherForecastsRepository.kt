package ru.mugz3m.weatherforecaster.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.datasource.OpenWeatherOneCallDataSource
import ru.mugz3m.weatherforecaster.data.model.DailyWeatherForecast

class DailyWeatherForecastsRepository(
    private val dataSource: OpenWeatherOneCallDataSource
) {
    private val _dailyWeatherForecast = MutableLiveData<DailyWeatherForecast>()
    val dailyWeatherForecast: LiveData<DailyWeatherForecast> = _dailyWeatherForecast

    @MainThread
    suspend fun updateDailyWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
        units: String,
        language: String
    ) {
        withContext(Dispatchers.IO) {
            dataSource.getDailyWeatherForecast(
                latitude,
                longitude,
                exclude,
                apiKey,
                units,
                language,
                _dailyWeatherForecast
            )
        }
    }
}
