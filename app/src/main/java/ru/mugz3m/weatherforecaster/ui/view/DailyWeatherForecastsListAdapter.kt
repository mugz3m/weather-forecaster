package ru.mugz3m.weatherforecaster.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.DailyWeatherForecastItemModel
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class DailyWeatherForecastsListAdapter(
    private val viewModel: WeatherViewModel,
    dailyWeatherForecastDiffCalculator: DailyWeatherForecastItemDiffCalculator
) : ListAdapter<DailyWeatherForecastItemModel, DailyWeatherForecastViewHolder>(
    dailyWeatherForecastDiffCalculator
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyWeatherForecastViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.daily_forecast_item,
            parent,
            false
        )
        return DailyWeatherForecastViewHolder(itemView, viewModel)
    }

    override fun onBindViewHolder(holder: DailyWeatherForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
