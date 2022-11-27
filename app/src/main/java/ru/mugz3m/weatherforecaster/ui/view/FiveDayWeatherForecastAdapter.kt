package ru.mugz3m.weatherforecaster.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.FiveDayWeatherForecastItemModel

class FiveDayWeatherForecastAdapter(
    dailyWeatherForecastDiffCalculator: FiveDayWeatherForecastItemDiffCalculator
) : ListAdapter<FiveDayWeatherForecastItemModel, FiveDayWeatherForecastViewHolder>(
    dailyWeatherForecastDiffCalculator
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FiveDayWeatherForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.five_day_weather_forecast_item,
            parent,
            false
        )
        return FiveDayWeatherForecastViewHolder(itemView, GlideImageLoader(parent.context))
    }

    override fun onBindViewHolder(holder: FiveDayWeatherForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
