package ru.mugz3m.weatherforecaster.ui.view

import androidx.recyclerview.widget.DiffUtil
import ru.mugz3m.weatherforecaster.ui.model.DailyWeatherForecastItemModel

class DailyWeatherForecastItemDiffCalculator :
    DiffUtil.ItemCallback<DailyWeatherForecastItemModel>() {
    override fun areItemsTheSame(
        oldItem: DailyWeatherForecastItemModel,
        newItem: DailyWeatherForecastItemModel
    ): Boolean {
        return oldItem.timeOfTheForecastedData == newItem.timeOfTheForecastedData
    }

    override fun areContentsTheSame(
        oldItem: DailyWeatherForecastItemModel,
        newItem: DailyWeatherForecastItemModel
    ): Boolean {
        return oldItem == newItem
    }
}
