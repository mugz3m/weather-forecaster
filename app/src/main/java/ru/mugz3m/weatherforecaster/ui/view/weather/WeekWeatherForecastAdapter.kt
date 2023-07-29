package ru.mugz3m.weatherforecaster.ui.view.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.WeekWeatherForecastItemModel

class WeekWeatherForecastAdapter(diffCalculator: WeekWeatherForecastItemDiffCalculator) :
    ListAdapter<WeekWeatherForecastItemModel, WeekWeatherForecastViewHolder>(diffCalculator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekWeatherForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.daily_weather_forecast_item,
            parent,
            false
        )
        return WeekWeatherForecastViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeekWeatherForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
