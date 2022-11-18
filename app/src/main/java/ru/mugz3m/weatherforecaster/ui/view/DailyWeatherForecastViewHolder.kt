package ru.mugz3m.weatherforecaster.ui.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.DailyWeatherForecastItemModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel
import java.util.*

class DailyWeatherForecastViewHolder(
    itemView: View,
    private val viewModel: WeatherViewModel
) : RecyclerView.ViewHolder(itemView) {

    private val date = itemView.findViewById<TextView>(R.id.daily_forecast_item_forecasted_date)
    private val icon =
        itemView.findViewById<ImageView>(R.id.daily_forecast_item_weather_weather_condition_icon)
    private val minimumTemperature =
        itemView.findViewById<TextView>(R.id.daily_forecast_item_minimum_daily_temperature)
    private val maximumTemperature =
        itemView.findViewById<TextView>(R.id.daily_forecast_item_maximum_daily_temperature)

    fun bind(daily: DailyWeatherForecastItemModel) {
        date.text = Date(daily.timeOfTheForecastedData * 1000L).toString()
        minimumTemperature.text = daily.minDailyTemperature.toString()
        maximumTemperature.text = daily.maxDailyTemperature.toString()
    }
}
