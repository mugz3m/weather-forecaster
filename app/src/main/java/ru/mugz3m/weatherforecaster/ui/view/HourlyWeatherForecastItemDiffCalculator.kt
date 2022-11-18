package ru.mugz3m.weatherforecaster.ui.view

import androidx.recyclerview.widget.DiffUtil
import ru.mugz3m.weatherforecaster.ui.model.HourlyWeatherForecastItemModel

class HourlyWeatherForecastItemDiffCalculator :
    DiffUtil.ItemCallback<HourlyWeatherForecastItemModel>() {
    override fun areItemsTheSame(
        oldItem: HourlyWeatherForecastItemModel,
        newItem: HourlyWeatherForecastItemModel
    ): Boolean {
        return oldItem.timeOfTheForecastedData == newItem.timeOfTheForecastedData
    }

    override fun areContentsTheSame(
        oldItem: HourlyWeatherForecastItemModel,
        newItem: HourlyWeatherForecastItemModel
    ): Boolean {
        return oldItem == newItem
    }
}
