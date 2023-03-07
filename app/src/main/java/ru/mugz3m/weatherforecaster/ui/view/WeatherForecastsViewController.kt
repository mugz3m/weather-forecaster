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
import ru.mugz3m.weatherforecaster.data.weather.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.weather.model.FiveDayWeatherForecastItem
import ru.mugz3m.weatherforecaster.ui.model.CurrentWeatherForecastModel
import ru.mugz3m.weatherforecaster.ui.model.FiveDayWeatherForecastItemModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class WeatherForecastsViewController(
    private val activity: Activity,
    rootView: View,
    private val fiveDayWeatherForecastAdapter: FiveDayWeatherForecastAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: WeatherViewModel,
    private val glideImageLoader: GlideImageLoader
) {
    private val temperature: TextView = rootView.findViewById(R.id.temperature)
    private val weatherConditionIcon: ImageView = rootView.findViewById(R.id.weather_condition_icon)
    private val weatherConditionDescription: TextView = rootView.findViewById(R.id.weather_condition_description)
    private val feelsLikeTemperature: TextView = rootView.findViewById(R.id.feels_like_temperature)
    private val wind: TextView = rootView.findViewById(R.id.wind)
    private val pressure: TextView = rootView.findViewById(R.id.pressure)
    private val humidity: TextView = rootView.findViewById(R.id.humidity)
    private val fiveDayWeatherForecastRecyclerView: RecyclerView =
        rootView.findViewById(R.id.five_day_forecast_recycler_view)
    private val swipeRefreshLayout: SwipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh_layout)

    fun setUpViews() {
        setProgress()
        setUpCurrentWeatherForecast()
        setUpFiveDayWeatherForecastsList()
        setUpSwipeToRefresh()
        setUpLocationObserver()
    }

    private fun setProgress() {
        viewModel.showProgress.observe(lifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
        }
    }

    private fun setUpCurrentWeatherForecast() {
        viewModel.currentWeatherForecast.observe(lifecycleOwner) { forecast ->
            val forecastModel = forecast.toItemModel()
            temperature.text = "${forecastModel.temperature} °C"
            glideImageLoader.loadWeatherIconInImageView(forecastModel.weatherIconId, weatherConditionIcon)
            weatherConditionDescription.text = forecastModel.weatherCondition.replaceFirstChar { it.uppercase() }
            feelsLikeTemperature.text = "${forecastModel.feelsLikeTemperature} °C"
            wind.text = "${forecastModel.windSpeed} m/s, ${forecastModel.windDirection}"
            pressure.text = "${forecastModel.atmosphericPressure} hPa"
            humidity.text = "${forecastModel.humidity} %"

            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setUpFiveDayWeatherForecastsList() {
        fiveDayWeatherForecastRecyclerView.layoutManager = LinearLayoutManager(activity)
        fiveDayWeatherForecastRecyclerView.adapter = fiveDayWeatherForecastAdapter
        viewModel.fiveDayWeatherForecast.observe(lifecycleOwner) { forecast ->
            fiveDayWeatherForecastAdapter.submitList(forecast.forecastsList.map { it.toItemModel() })
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
        }
    }

    private fun CurrentWeatherForecast.toItemModel() = CurrentWeatherForecastModel(
        weatherParameters.temperature,
        weatherParameters.feelsLikeTemperature,
        weatherParameters.atmosphericPressure,
        weatherParameters.humidity,
        wind.windSpeed,
        wind.windDirection,
        weatherConditions[0].weatherCondition,
        weatherConditions[0].weatherIconId
    )

    private fun FiveDayWeatherForecastItem.toItemModel() = FiveDayWeatherForecastItemModel(
        timeOfTheForecastedData,
        weatherParameters.temperature,
        weatherConditions[0].weatherIconId
    )
}
