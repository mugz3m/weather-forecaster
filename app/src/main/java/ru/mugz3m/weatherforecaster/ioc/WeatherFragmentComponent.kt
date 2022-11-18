package ru.mugz3m.weatherforecaster.ioc

import androidx.fragment.app.Fragment
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel
import ru.mugz3m.weatherforecaster.ui.view.FiveDayWeatherForecastAdapter
import ru.mugz3m.weatherforecaster.ui.view.FiveDayWeatherForecastItemDiffCalculator

class WeatherFragmentComponent(
    val fragment: Fragment,
    val viewModel: WeatherViewModel
) {
    val fiveDayWeatherForecastAdapter =
        FiveDayWeatherForecastAdapter(
            viewModel,
            FiveDayWeatherForecastItemDiffCalculator()
        )
}
