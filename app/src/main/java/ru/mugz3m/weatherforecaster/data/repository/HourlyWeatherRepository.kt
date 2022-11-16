package ru.mugz3m.weatherforecaster.data.repository

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.datasource.OpenWeatherOneCallDataSource
import ru.mugz3m.weatherforecaster.data.model.HourlyWeatherForecastResponseBody

class HourlyWeatherRepository(
    private val dataSource: OpenWeatherOneCallDataSource
) {
    private val _hourlyWeatherForecast = MutableLiveData<HourlyWeatherForecastResponseBody>()
    val hourlyWeatherForecast: LiveData<HourlyWeatherForecastResponseBody> = _hourlyWeatherForecast

    @MainThread
    suspend fun updateHourlyWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
        units: String,
        language: String
    ) {
        withContext(Dispatchers.IO) {
            dataSource.getHourlyWeatherForecast(
                latitude,
                longitude,
                exclude,
                apiKey,
                units,
                language,
                _hourlyWeatherForecast
            )
        }
    }
}
