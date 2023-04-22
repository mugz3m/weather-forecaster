package ru.mugz3m.weatherforecaster.ioc

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mugz3m.weatherforecaster.data.location.datasource.LocationDataSource
import ru.mugz3m.weatherforecaster.data.location.repository.LocationRepository
import ru.mugz3m.weatherforecaster.data.weather.datasource.OpenMeteoDataSource
import ru.mugz3m.weatherforecaster.data.weather.repository.WeatherForecastRepository

class ApplicationComponent(applicationContext: Context) {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherDataSource = OpenMeteoDataSource(retrofit)
    private val locationDataSource: LocationDataSource = LocationDataSource(applicationContext)

    private val weatherForecastRepository = WeatherForecastRepository(weatherDataSource)
    private val locationRepository = LocationRepository(locationDataSource)

    val viewModelFactory = ViewModelFactory(weatherForecastRepository, locationRepository)
}
