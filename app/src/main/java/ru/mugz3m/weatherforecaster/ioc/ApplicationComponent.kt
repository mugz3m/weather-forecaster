package ru.mugz3m.weatherforecaster.ioc

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.mugz3m.weatherforecaster.data.datasource.OpenWeatherOneCallDataSource
import ru.mugz3m.weatherforecaster.data.repository.CurrentWeatherForecastRepository
import ru.mugz3m.weatherforecaster.data.repository.DailyWeatherForecastsRepository
import ru.mugz3m.weatherforecaster.data.repository.HourlyWeatherForecastsRepository

class ApplicationComponent {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherDataSource = OpenWeatherOneCallDataSource(retrofit)

    private val currentWeatherForecastRepository =
        CurrentWeatherForecastRepository(weatherDataSource)

    private val hourlyWeatherForecastsRepository =
        HourlyWeatherForecastsRepository(weatherDataSource)

    private val dailyWeatherForecastsRepository = DailyWeatherForecastsRepository(weatherDataSource)

    val viewModelFactory = ViewModelFactory(
        currentWeatherForecastRepository,
        hourlyWeatherForecastsRepository,
        dailyWeatherForecastsRepository
    )
}
