package ru.mugz3m.weatherforecaster.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.mugz3m.weatherforecaster.R
import ru.mugz3m.weatherforecaster.data.location.LocationPermissionManager

class MainActivity : AppCompatActivity() {
    private val locationPermissionManager = LocationPermissionManager(activity = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getLocationPermissionGrant()
    }

    private fun getLocationPermissionGrant() {
        if (!locationPermissionManager.isCoarseAndFineLocationPermissionGranted()) {
            locationPermissionManager.requestCoarseAndFineLocationPermission(
                LocationPermissionManager.LOCATION_PERMISSION_CODE
            )
        }
    }
}
