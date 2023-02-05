package ru.mugz3m.weatherforecaster.data.location.datasource

import android.content.Context
import android.location.Location
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LocationDataSource(private val context: Context) {
    private val locationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    fun initLocationClient() {
        locationClient = DefaultLocationClient(
            context.applicationContext,
            LocationServices.getFusedLocationProviderClient(context.applicationContext)
        )
    }

    @MainThread
    fun subscribeToLocationUpdates(getCurrentLocationLiveData: MutableLiveData<Location>) {
        locationClient.getLocationUpdates(LocationClient.DEFAULT_LOCATION_UPDATES_INTERVAL_MILLIS)
            .catch { e ->
                e.printStackTrace()
                Log.e(TAG, e.message.toString())
            }
            .onEach { location ->
                getCurrentLocationLiveData.postValue(location)
            }
            .launchIn(locationScope)
    }

    fun cancelLocationScopeJobs() {
        locationScope.cancel()
    }

    companion object {
        private const val TAG = "LOCATION_DATA_SOURCE"
    }
}
