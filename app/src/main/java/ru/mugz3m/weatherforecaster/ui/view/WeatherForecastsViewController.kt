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

    private val currentWeatherForecastTemperature: TextView = rootView.findViewById(R.id.fragment_weather_temperature)
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
    private val currentWeatherForecastWeatherPressure: TextView = rootView.findViewById(R.id.fragment_weather_pressure)
    private val currentWeatherForecastWeatherHumidity: TextView = rootView.findViewById(R.id.fragment_weather_humidity)
    private val fiveDayWeatherForecastRecyclerView: RecyclerView =
        rootView.findViewById(R.id.fragment_weather_five_day_forecast_recycler_view)
    private val swipeRefreshLayout: SwipeRefreshLayout =
        rootView.findViewById(R.id.fragment_weather_swipe_refresh_layout)

    fun setUpViews() {
        swipeRefreshLayout.isRefreshing = true
        setUpCurrentWeatherForecast()
        setUpFiveDayWeatherForecastsList()
        setUpSwipeToRefresh()
        setUpLocationObserver()
    }

    private fun setUpCurrentWeatherForecast() {
        viewModel.currentWeatherForecast.observe(lifecycleOwner) { forecast ->
            val forecastModel =
                weatherForecastDataTransformer.transformCurrentWeatherForecastToCurrentWeatherForecastModel(forecast)
            currentWeatherForecastTemperature.text = forecastModel.temperature.toString().plus(" °C")
            glideImageLoader.loadWeatherIconInImageView(
                forecastModel.weatherIconId,
                currentWeatherForecastWeatherConditionIcon
            )
            currentWeatherForecastWeatherConditionDescription.text = forecastModel.weatherCondition.replaceFirstChar { it.uppercase() }
            currentWeatherForecastWeatherFeelsLikeTemperature.text = forecastModel.feelsLikeTemperature.toString().plus(" °C")
            currentWeatherForecastWeatherWindSpeed.text = forecastModel.windSpeed.toString()
            currentWeatherForecastWeatherWindDirection.text = forecastModel.windDirection.toString()
            currentWeatherForecastWeatherPressure.text = forecastModel.atmosphericPressure.toString()
            currentWeatherForecastWeatherHumidity.text = forecastModel.humidity.toString()

            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpFiveDayWeatherForecastsList() {
        fiveDayWeatherForecastRecyclerView.layoutManager = LinearLayoutManager(activity)
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

    private fun setUpLocationObserver() {
        viewModel.currentLocation.observe(lifecycleOwner) {
            viewModel.updateAllWeatherForecasts()
            swipeRefreshLayout.isRefreshing = false
        }
    }
}
