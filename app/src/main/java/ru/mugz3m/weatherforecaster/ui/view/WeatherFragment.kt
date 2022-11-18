package ru.mugz3m.weatherforecaster.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.mugz3m.weatherforecaster.App
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ioc.WeatherFragmentComponent
import ru.mugz3m.weatherforecaster.ioc.WeatherFragmentViewComponent
import ru.mugz3m.weatherforecaster.ui.stateholders.WeatherViewModel

class WeatherFragment : Fragment() {

    private val applicationComponent
        get() = App.get(requireContext()).applicationComponent
    private lateinit var fragmentComponent: WeatherFragmentComponent
    private var fragmentViewComponent: WeatherFragmentViewComponent? = null

    private val viewModel: WeatherViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent = WeatherFragmentComponent(
            applicationComponent,
            fragment = this,
            viewModel = viewModel
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_weather, container, false)
        fragmentViewComponent = WeatherFragmentViewComponent(
            fragmentComponent,
            root = view,
            lifecycleOwner = viewLifecycleOwner
        ).apply {
            weatherForecastsViewController.setUpViews()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentViewComponent = null
    }
}
