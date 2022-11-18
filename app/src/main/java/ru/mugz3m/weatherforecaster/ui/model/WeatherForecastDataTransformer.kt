package ru.mugz3m.weatherforecaster.ui.model

import ru.mugz3m.weatherforecaster.data.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.FiveDayWeatherForecastItem

class WeatherForecastDataTransformer {
    fun transformCurrentWeatherForecastToCurrentWeatherForecastModel(
        currentWeatherForecast: CurrentWeatherForecast
    ): CurrentWeatherForecastModel {
        return CurrentWeatherForecastModel(
            currentWeatherForecast.weatherParameters.temperature,
            currentWeatherForecast.weatherParameters.feelsLikeTemperature,
            currentWeatherForecast.weatherParameters.atmosphericPressure,
            currentWeatherForecast.weatherParameters.humidity,
            currentWeatherForecast.wind.windSpeed,
            currentWeatherForecast.wind.windDirection,
            currentWeatherForecast.weatherConditions[0].weatherCondition,
            currentWeatherForecast.weatherConditions[0].weatherIconId
        )
    }

    private fun transformFiveDayWeatherForecastItemToFiveDayWeatherForecastModel(
        fiveDayWeatherForecastItem: FiveDayWeatherForecastItem
    ): FiveDayWeatherForecastItemModel {
        return FiveDayWeatherForecastItemModel(
            fiveDayWeatherForecastItem.timeOfTheForecastedData,
            fiveDayWeatherForecastItem.weatherParameters.temperature,
            fiveDayWeatherForecastItem.weatherConditions[0].weatherIconId
        )
    }

    fun transformFiveDayWeatherForecastItemListToFiveDayWeatherForecastModelList(
        fiveDayWeatherForecastItems: List<FiveDayWeatherForecastItem>
    ): List<FiveDayWeatherForecastItemModel> =
        fiveDayWeatherForecastItems.map { it ->
            transformFiveDayWeatherForecastItemToFiveDayWeatherForecastModel(it)
        }
}
