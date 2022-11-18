package ru.mugz3m.weatherforecaster.ui.view

import android.app.Activity
import android.location.Location
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
    private val viewModel: WeatherViewModel
) {
    private val weatherForecastDataTransformer = WeatherForecastDataTransformer()
    private val lastLocation: Location? = null

    private val currentWeatherForecastTemperature: TextView =
        rootView.findViewById(R.id.fragment_weather_temperature)
    private val currentWeatherForecastWeatherConditionIcon: ImageView =
        rootView.findViewById(R.id.fragment_weather_weather_condition_icon)
    private val currentWeatherForecastWeatherConditionDescription: TextView =
        rootView.findViewById(R.id.fragment_weather_weather_condition_description)
    private val currentWeatherForecastWeatherFeelsLikeTemperature: TextView =
        rootView.findViewById(R.id.fragment_weather_feels_like_temperature)
    private val currentWeatherForecastWeatherWindSpeed: TextView =
        rootView.findViewById(R.id.fragment_weather_wind_speed)
    private val currentWeatherForecastWeatherWindDirection: TextView =
        rootView.findViewById(R.id.fragment_weather_wind_direction)
    private val currentWeatherForecastWeatherPressure: TextView =
        rootView.findViewById(R.id.fragment_weather_pressure)
    private val currentWeatherForecastWeatherHumidity: TextView =
        rootView.findViewById(R.id.fragment_weather_humidity)

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
            currentWeatherForecastTemperature.text =
                newCurrentWeatherForecastModel.temperature.toString().plus(" °C")
            currentWeatherForecastWeatherConditionDescription.text =
                newCurrentWeatherForecastModel.weatherCondition
            currentWeatherForecastWeatherFeelsLikeTemperature.text =
                newCurrentWeatherForecastModel.feelsLikeTemperature.toString().plus(" °C")
            currentWeatherForecastWeatherWindSpeed.text =
                newCurrentWeatherForecastModel.windSpeed.toString()
            currentWeatherForecastWeatherWindDirection.text =
                newCurrentWeatherForecastModel.windDirection.toString()
            currentWeatherForecastWeatherPressure.text =
                newCurrentWeatherForecastModel.atmosphericPressure.toString()
            currentWeatherForecastWeatherHumidity.text =
                newCurrentWeatherForecastModel.humidity.toString()

            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpFiveDayWeatherForecastsList() {
        fiveDayWeatherForecastRecyclerView.layoutManager =
            LinearLayoutManager(activity)
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
