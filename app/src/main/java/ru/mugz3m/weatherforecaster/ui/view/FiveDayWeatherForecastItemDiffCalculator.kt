package ru.mugz3m.weatherforecaster.ui.view

import androidx.recyclerview.widget.DiffUtil
import ru.mugz3m.weatherforecaster.ui.model.FiveDayWeatherForecastItemModel

class FiveDayWeatherForecastItemDiffCalculator : DiffUtil.ItemCallback<FiveDayWeatherForecastItemModel>() {
    override fun areItemsTheSame(
        oldItem: FiveDayWeatherForecastItemModel,
        newItem: FiveDayWeatherForecastItemModel
    ): Boolean {
        return oldItem.timeOfTheForecastedData == newItem.timeOfTheForecastedData
    }

    override fun areContentsTheSame(
        oldItem: FiveDayWeatherForecastItemModel,
        newItem: FiveDayWeatherForecastItemModel
    ): Boolean {
        return oldItem == newItem
    }
}
