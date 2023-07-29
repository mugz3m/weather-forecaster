package ru.mugz3m.weatherforecaster.ui.view.weather

import androidx.recyclerview.widget.DiffUtil
import ru.mugz3m.weatherforecaster.ui.model.WeekWeatherForecastItemModel

class WeekWeatherForecastItemDiffCalculator : DiffUtil.ItemCallback<WeekWeatherForecastItemModel>() {
    override fun areItemsTheSame(
        oldItem: WeekWeatherForecastItemModel,
        newItem: WeekWeatherForecastItemModel
    ): Boolean {
        return oldItem.dateTime.contentEquals(newItem.dateTime)
    }

    override fun areContentsTheSame(
        oldItem: WeekWeatherForecastItemModel,
        newItem: WeekWeatherForecastItemModel
    ): Boolean {
        return oldItem == newItem
    }
}
