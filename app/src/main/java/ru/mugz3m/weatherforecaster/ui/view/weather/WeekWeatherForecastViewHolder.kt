package ru.mugz3m.weatherforecaster.ui.view.weather

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.model.WeekWeatherForecastItemModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


class WeekWeatherForecastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val date = itemView.findViewById<TextView>(R.id.itemDate)
    private val icon = itemView.findViewById<ImageView>(R.id.itemWeatherCondition)
    private val temperatureMax = itemView.findViewById<TextView>(R.id.itemMaxTemperature)
    private val temperatureMin = itemView.findViewById<TextView>(R.id.itemMinTemperature)

    fun bind(itemModel: WeekWeatherForecastItemModel) {
        date.text = formatIsoStringToDate(itemModel.dateTime)
        temperatureMax.text = itemModel.temperatureMax.toString().plus(" °C")
        temperatureMin.text = itemModel.temperatureMax.toString().plus(" °C")
    }

    private fun formatIsoStringToDate(isoString: String): String {
        return try {
            val date = LocalDate.parse(isoString, DateTimeFormatter.ofPattern(API_DATE_TIME_FORMAT))
            date.format(DateTimeFormatter.ofPattern(ITEM_DATE_TIME_FORMAT))
        } catch (e: DateTimeParseException) {
            Log.d(TAG, e.message.toString())
            isoString
        }
    }

    companion object {
        private val TAG = this::class.simpleName
        private const val API_DATE_TIME_FORMAT = "yyyy-MM-dd"
        private const val ITEM_DATE_TIME_FORMAT = "EEE, MMM dd"
    }
}
