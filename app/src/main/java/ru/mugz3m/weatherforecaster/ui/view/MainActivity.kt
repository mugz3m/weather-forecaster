package ru.mugz3m.weatherforecaster.ui.view

import android.Manifest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import ru.mugz3m.weatherforecaster.App
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.ui.stateholders.LocationViewModel

class MainActivity : AppCompatActivity() {
    private val applicationComponent get() = App.get(context = this).applicationComponent
    private val viewModel: LocationViewModel by viewModels { applicationComponent.viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            0
        )

        viewModel.initLocationClient()
        viewModel.subscribeToLocationUpdates()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.cancelLocationScopeJobs()
    }
}
