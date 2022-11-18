package ru.mugz3m.weatherforecaster.ui.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.HourlyWeatherForecastItemModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel
import java.util.*

class HourlyWeatherForecastViewHolder(
    itemView: View,
    private val viewModel: WeatherViewModel
) : RecyclerView.ViewHolder(itemView) {

    private val time = itemView.findViewById<TextView>(R.id.hourly_forecast_item_forecasted_time)
    private val icon =
        itemView.findViewById<ImageView>(R.id.hourly_forecast_item_weather_condition_icon)
    private val temperature =
        itemView.findViewById<TextView>(R.id.hourly_forecast_item_temperature)

    fun bind(hourly: HourlyWeatherForecastItemModel) {
        time.text = Date(hourly.timeOfTheForecastedData * 1000L).toString()
        temperature.text = hourly.temperature.toString()
    }
}
