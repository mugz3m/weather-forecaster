package ru.mugz3m.weatherforecaster.ui.view

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.FiveDayWeatherForecastItemModel
import java.text.SimpleDateFormat
import java.util.*

class FiveDayWeatherForecastViewHolder(itemView: View, private val glideImageLoader: GlideImageLoader) :
    RecyclerView.ViewHolder(itemView) {
    private val date = itemView.findViewById<TextView>(R.id.five_day_weather_forecast_item_forecasted_date)
    private val icon = itemView.findViewById<ImageView>(R.id.five_day_weather_forecast_item_weather_condition_icon)
    private val temperature = itemView.findViewById<TextView>(R.id.five_day_weather_forecast_item_temperature)

    fun bind(fiveDayWeatherForecast: FiveDayWeatherForecastItemModel) {
        date.text = formatUnixTimeToDate(fiveDayWeatherForecast.timeOfTheForecastedData)
        glideImageLoader.loadWeatherIconInImageView(fiveDayWeatherForecast.weatherIconId, icon)
        temperature.text = fiveDayWeatherForecast.temperature.toString().plus(" °C")
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatUnixTimeToDate(unixTime: Long): String {
        val dateFormat = SimpleDateFormat("dd/MM HH:mm")
        return dateFormat.format(Date(unixTime * 1000L))
    }
}
