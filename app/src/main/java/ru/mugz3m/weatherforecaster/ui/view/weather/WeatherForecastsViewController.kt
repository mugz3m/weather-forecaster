package ru.mugz3m.weatherforecaster.ui.view.weather

import android.app.Application
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.data.weather.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.weather.model.DailyWeather
import ru.mugz3m.weatherforecaster.ui.model.CurrentWeatherForecastModel
import ru.mugz3m.weatherforecaster.ui.model.WeekWeatherForecastItemModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class WeatherForecastsViewController(
    private val appContext: Application,
    rootView: View,
    private val weekWeatherForecastAdapter: WeekWeatherForecastAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: WeatherViewModel
) {
    private val temperature: TextView = rootView.findViewById(R.id.temperature)
    private val weatherConditionIcon: ImageView = rootView.findViewById(R.id.weatherConditionIcon)
    private val weatherConditionDescription: TextView = rootView.findViewById(R.id.weatherConditionDescription)
    private val wind: TextView = rootView.findViewById(R.id.wind)
    private val dailyForecasts: RecyclerView = rootView.findViewById(R.id.dailyForecasts)
    private val swipeRefreshLayout: SwipeRefreshLayout = rootView.findViewById(R.id.swipeRefreshLayout)

    fun setUpViews() {
        setProgress()
        setUpCurrentWeatherForecast()
        setUpWeekWeatherForecastsList()
        setUpSwipeToRefresh()
        setUpLocationObserver()
    }

    private fun setProgress() {
        viewModel.showProgress.observe(lifecycleOwner) {
            swipeRefreshLayout.isRefreshing = it
        }
    }

    private fun setUpCurrentWeatherForecast() {
        viewModel.currentWeatherForecast.observe(lifecycleOwner) {
            if (it.isSuccess) {
                try {
                    val itemModel = it.getOrThrow().toItemModel()
                    temperature.text = "${itemModel.temperature} °C"
                    wind.text = "${itemModel.windSpeed} m/s, ${itemModel.windDirection}"
                } catch (t: Throwable) {
                    Log.d(TAG, t.message.toString())
                }
            }
        }
    }

    private fun setUpWeekWeatherForecastsList() {
        viewModel.weekWeatherForecast.observe(lifecycleOwner) {
            if (it.isSuccess) {
                dailyForecasts.layoutManager = LinearLayoutManager(appContext)
                dailyForecasts.adapter = weekWeatherForecastAdapter

                val list = mutableListOf<WeekWeatherForecastItemModel>()
                val dailyWeather = it.getOrNull()?.daily
                if (dailyWeather != null) {
                    val dailyWeatherSize = minOf(
                        dailyWeather.time.size,
                        dailyWeather.weatherCode.size,
                        dailyWeather.temperatureMin.size,
                        dailyWeather.temperatureMax.size
                    )
                    for (index in 0 until dailyWeatherSize) {
                        list.add(dailyWeather.toItemModel(index))
                    }
                }
                weekWeatherForecastAdapter.submitList(list)
            }
        }
        swipeRefreshLayout.isRefreshing = false
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
        temperature = currentWeather.temperature,
        windSpeed = currentWeather.windSpeed,
        windDirection = currentWeather.windDirection,
        weatherCode = currentWeather.weatherCode
    )

    private fun DailyWeather.toItemModel(index: Int) = WeekWeatherForecastItemModel(
        dateTime = time[index],
        weatherCode = weatherCode[index],
        temperatureMax = temperatureMax[index],
        temperatureMin = temperatureMin[index]
    )

    companion object {
        private val TAG = this::class.simpleName
    }
}
