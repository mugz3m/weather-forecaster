package ru.mugz3m.weatherforecaster.ioc

import androidx.fragment.app.Fragment
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel
import ru.mugz3m.weatherforecaster.ui.view.DailyWeatherForecastItemDiffCalculator
import ru.mugz3m.weatherforecaster.ui.view.DailyWeatherForecastsListAdapter
import ru.mugz3m.weatherforecaster.ui.view.HourlyWeatherForecastItemDiffCalculator
import ru.mugz3m.weatherforecaster.ui.view.HourlyWeatherForecastsListAdapter

class WeatherFragmentComponent(
    val applicationComponent: ApplicationComponent,
    val fragment: Fragment,
    val viewModel: WeatherViewModel
) {
    val hourlyForecastAdapter =
        HourlyWeatherForecastsListAdapter(viewModel, HourlyWeatherForecastItemDiffCalculator())
    val dailyForecastAdapter =
        DailyWeatherForecastsListAdapter(viewModel, DailyWeatherForecastItemDiffCalculator())
}
