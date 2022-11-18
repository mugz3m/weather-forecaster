package ru.mugz3m.weatherforecaster.ioc

import android.view.View
import androidx.lifecycle.LifecycleOwner
import ru.mugz3m.weatherforecaster.ui.view.WeatherForecastsViewController

class WeatherFragmentViewComponent(
    fragmentComponent: WeatherFragmentComponent,
    root: View,
    lifecycleOwner: LifecycleOwner
) {
    val weatherForecastsViewController = WeatherForecastsViewController(
        fragmentComponent.fragment.requireActivity(),
        root,
        fragmentComponent.hourlyForecastAdapter,
        fragmentComponent.dailyForecastAdapter,
        lifecycleOwner,
        fragmentComponent.viewModel
    )
}
