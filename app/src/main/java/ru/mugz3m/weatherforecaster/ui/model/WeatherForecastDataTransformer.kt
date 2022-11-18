package ru.mugz3m.weatherforecaster.ui.model

import ru.mugz3m.weatherforecaster.data.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.Daily
import ru.mugz3m.weatherforecaster.data.model.Hourly

class WeatherForecastDataTransformer {
    fun transformCurrentWeatherForecastToCurrentWeatherModel(
        currentWeatherForecast: CurrentWeatherForecast
    ): CurrentWeatherForecastModel {
        return CurrentWeatherForecastModel(
            currentWeatherForecast.current.temperature,
            currentWeatherForecast.current.feelsLikeTemperature,
            currentWeatherForecast.current.atmosphericPressure,
            currentWeatherForecast.current.humidity,
            currentWeatherForecast.current.windSpeed,
            currentWeatherForecast.current.windDirection,
            currentWeatherForecast.current.weatherConditions.weatherCondition,
            currentWeatherForecast.current.weatherConditions.weatherIconId
        )
    }

    fun transformHourlyToHourlyWeatherItemModel(hourly: Hourly): HourlyWeatherForecastItemModel {
        return HourlyWeatherForecastItemModel(
            hourly.timeOfTheForecastedData,
            hourly.temperature,
            hourly.weatherConditions.weatherIconId,
        )
    }

    fun transformHourlyListToHourlyWeatherItemModelList(hourlyList: List<Hourly>): List<HourlyWeatherForecastItemModel> =
        hourlyList.map { it -> transformHourlyToHourlyWeatherItemModel(it) }

    fun transformDailyToDailyWeatherItemModel(daily: Daily): DailyWeatherForecastItemModel {
        return DailyWeatherForecastItemModel(
            daily.timeOfTheForecastedData,
            daily.temperaturesDuringTheDay.minDaily,
            daily.temperaturesDuringTheDay.maxDaily,
            daily.weatherConditions.weatherIconId
        )
    }

    fun transformDailyListToDailyWeatherItemModelList(dailyList: List<Daily>): List<DailyWeatherForecastItemModel> =
        dailyList.map { it -> transformDailyToDailyWeatherItemModel(it) }
}
