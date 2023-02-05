package ru.mugz3m.weatherforecaster.data.location.repository

import android.location.Location
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.location.datasource.LocationDataSource

class LocationRepository(private val dataSource: LocationDataSource) {
    private val _currentLocation = MutableLiveData<Location>()
    val currentLocation: LiveData<Location> = _currentLocation

    fun initLocationClient() {
        dataSource.initLocationClient()
    }

    @MainThread
    suspend fun subscribeToLocationUpdates() {
        withContext(Dispatchers.IO) {
            dataSource.subscribeToLocationUpdates(_currentLocation)
        }
    }

    fun cancelLocationScopeJobs() {
        dataSource.cancelLocationScopeJobs()
    }
}
