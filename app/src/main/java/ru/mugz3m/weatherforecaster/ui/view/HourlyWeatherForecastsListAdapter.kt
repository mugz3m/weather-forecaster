package ru.mugz3m.weatherforecaster.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.HourlyWeatherForecastItemModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class HourlyWeatherForecastsListAdapter(
    private val viewModel: WeatherViewModel,
    hourlyWeatherForecastDiffCalculator: HourlyWeatherForecastItemDiffCalculator
) : ListAdapter<HourlyWeatherForecastItemModel, HourlyWeatherForecastViewHolder>(
    hourlyWeatherForecastDiffCalculator
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HourlyWeatherForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.daily_forecast_item,
            parent,
            false
        )
        return HourlyWeatherForecastViewHolder(itemView, viewModel)
    }

    override fun onBindViewHolder(holder: HourlyWeatherForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
