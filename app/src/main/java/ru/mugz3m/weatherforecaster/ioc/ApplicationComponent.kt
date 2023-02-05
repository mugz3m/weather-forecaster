package ru.mugz3m.weatherforecaster.ioc

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mugz3m.weatherforecaster.data.location.datasource.LocationDataSource
import ru.mugz3m.weatherforecaster.data.location.repository.LocationRepository
import ru.mugz3m.weatherforecaster.data.weather.datasource.OpenWeatherOneCallDataSource
import ru.mugz3m.weatherforecaster.data.weather.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.weather.repository.FiveDayWeatherForecastRepository

class ApplicationComponent(applicationContext: Context) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherDataSource = OpenWeatherOneCallDataSource(retrofit)

    private val locationDataSource: LocationDataSource = LocationDataSource(applicationContext)

    private val currentWeatherForecastRepository =
        CurrentWeatherForecastRepository(weatherDataSource)

    private val fiveDayWeatherForecastRepository =
        FiveDayWeatherForecastRepository(weatherDataSource)

    private val locationRepository = LocationRepository(locationDataSource)

    val viewModelFactory = ViewModelFactory(
        currentWeatherForecastRepository,
        fiveDayWeatherForecastRepository,
        locationRepository
    )
}
