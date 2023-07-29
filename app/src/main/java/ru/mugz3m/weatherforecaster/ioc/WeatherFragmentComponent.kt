package ru.mugz3m.weatherforecaster.ioc

import androidx.fragment.app.Fragment
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel
import ru.mugz3m.weatherforecaster.ui.view.weather.WeekWeatherForecastAdapter
import ru.mugz3m.weatherforecaster.ui.view.weather.WeekWeatherForecastItemDiffCalculator

class WeatherFragmentComponent(
    val fragment: Fragment,
    val viewModel: WeatherViewModel
) {
    val fiveDayWeatherForecastAdapter =
        WeekWeatherForecastAdapter(
            WeekWeatherForecastItemDiffCalculator()
        )
}
