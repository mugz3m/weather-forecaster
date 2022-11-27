package ru.mugz3m.weatherforecaster.ui.view

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.WeatherForecastDataTransformer
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class WeatherForecastsViewController(
    private val activity: Activity,
    rootView: View,
    private val fiveDayWeatherForecastAdapter: FiveDayWeatherForecastAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: WeatherViewModel,
    private val glideImageLoader: GlideImageLoader
) {
    private val weatherForecastDataTransformer = WeatherForecastDataTransformer()

    private val temperatureTextView: TextView =
        rootView.findViewById(R.id.fragment_weather_temperature)
    private val weatherConditionIconImageView: ImageView =
        rootView.findViewById(R.id.fragment_weather_weather_condition_icon)
    private val weatherConditionDescriptionTextView: TextView =
        rootView.findViewById(R.id.fragment_weather_weather_condition_description)
    private val feelsLikeTemperatureTextView: TextView =
        rootView.findViewById(R.id.fragment_weather_feels_like_temperature)
    private val windSpeedTextView: TextView =
        rootView.findViewById(R.id.fragment_weather_wind_speed)
    private val pressureTextView: TextView = rootView.findViewById(R.id.fragment_weather_pressure)
    private val humidityTextView: TextView = rootView.findViewById(R.id.fragment_weather_humidity)

    private val fiveDayWeatherForecastRecyclerView: RecyclerView =
        rootView.findViewById(R.id.fragment_weather_five_day_forecast_recycler_view)
    private val swipeRefreshLayout: SwipeRefreshLayout =
        rootView.findViewById(R.id.fragment_weather_swipe_refresh_layout)

    fun setUpViews() {
        setUpCurrentWeatherForecast()
        setUpFiveDayWeatherForecastsList()
        setUpSwipeToRefresh()
    }

    private fun setUpCurrentWeatherForecast() {
        viewModel.currentWeatherForecast.observe(lifecycleOwner) { newCurrentWeatherForecast ->
            val newCurrentWeatherForecastModel =
                weatherForecastDataTransformer.transformCurrentWeatherForecastToCurrentWeatherForecastModel(
                    newCurrentWeatherForecast
                )

            temperatureTextView.text =
                newCurrentWeatherForecastModel.temperature.toString().plus(" °C")

            glideImageLoader.loadWeatherIconInImageView(
                newCurrentWeatherForecastModel.weatherIconId,
                weatherConditionIconImageView
            )
            weatherConditionDescriptionTextView.text =
                newCurrentWeatherForecastModel.weatherCondition

            feelsLikeTemperatureTextView.text =
                newCurrentWeatherForecastModel.feelsLikeTemperature.toString().plus(" °C")

            when (newCurrentWeatherForecastModel.windDirection) {
                WindDirections.NORTH.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                WindDirections.NORTHEAST.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                WindDirections.EAST.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                WindDirections.SOUTHEAST.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                WindDirections.SOUTH.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                WindDirections.SOUTHWEST.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                WindDirections.WEST.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                WindDirections.NORTHWEST.degree -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
                else -> windSpeedTextView.text =
                    newCurrentWeatherForecastModel.windSpeed.toString()
                        .plus(" m/s")
                        .plus(", ${WindDirections.NORTH.abbreviation}")
            }

            pressureTextView.text =
                newCurrentWeatherForecastModel.atmosphericPressure.toString().plus(", hPa")

            humidityTextView.text =
                newCurrentWeatherForecastModel.humidity.toString().plus(" %")

            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpFiveDayWeatherForecastsList() {
        fiveDayWeatherForecastRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        fiveDayWeatherForecastRecyclerView.adapter = fiveDayWeatherForecastAdapter
        viewModel.fiveDayWeatherForecast.observe(lifecycleOwner) { newFiveDayWeatherForecast ->
            fiveDayWeatherForecastAdapter.submitList(
                weatherForecastDataTransformer.transformFiveDayWeatherForecastItemListToFiveDayWeatherForecastModelList(
                    newFiveDayWeatherForecast.forecastsList
                )
            )
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateAllWeatherForecasts()
        }
    }
}

enum class WindDirections(val degree: Int, val abbreviation: String) {
    NORTH(0, "N"),
    NORTHEAST(45, "NE"),
    EAST(90, "E"),
    SOUTHEAST(135, "SE"),
    SOUTH(180, "S"),
    SOUTHWEST(225, "SW"),
    WEST(270, "W"),
    NORTHWEST(315, "NW")
}
