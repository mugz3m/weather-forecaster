package ru.mugz3m.weatherforecaster.ui.view

import android.app.Activity
import android.view.View
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
    private val hourlyWeatherForecastsAdapter: HourlyWeatherForecastsListAdapter,
    private val dailyWeatherForecastsAdapter: DailyWeatherForecastsListAdapter,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: WeatherViewModel,
) {
    private val weatherForecastDataTransformer = WeatherForecastDataTransformer()

    private val hourlyWeatherForecastRecyclerView: RecyclerView =
        rootView.findViewById(R.id.fragment_weather_hourly_forecast_recycler_view)
    private val dailyWeatherForecastRecyclerView: RecyclerView =
        rootView.findViewById(R.id.fragment_weather_daily_forecast_recycler_view)
    private val swipeRefreshLayout: SwipeRefreshLayout =
        rootView.findViewById(R.id.fragment_weather_swipe_refresh_layout)

    fun setUpViews() {
        setUpHourlyForecastsList()
        setUpDailyForecastsList()
        setUpSwipeToRefresh()
    }

    fun setUpHourlyForecastsList() {
        hourlyWeatherForecastRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        hourlyWeatherForecastRecyclerView.adapter = hourlyWeatherForecastsAdapter
        viewModel.hourlyWeatherForecast.observe(lifecycleOwner) { newHourlyWeatherForecast ->
            hourlyWeatherForecastsAdapter.submitList(
                weatherForecastDataTransformer.transformHourlyListToHourlyWeatherItemModelList(
                    newHourlyWeatherForecast.hourlyWeatherList
                )
            )
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun setUpDailyForecastsList() {
        dailyWeatherForecastRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        dailyWeatherForecastRecyclerView.adapter = dailyWeatherForecastsAdapter
        viewModel.dailyWeatherForecast.observe(lifecycleOwner) { newDailyWeatherForecast ->
            dailyWeatherForecastsAdapter.submitList(
                weatherForecastDataTransformer.transformDailyListToDailyWeatherItemModelList(
                    newDailyWeatherForecast.dailyWeatherList
                )
            )
            swipeRefreshLayout.isRefreshing = false
        }
    }

    fun setUpSwipeToRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateAllWeatherForecasts()
        }
    }
}
