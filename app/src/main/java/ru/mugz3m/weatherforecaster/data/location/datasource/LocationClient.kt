package ru.mugz3m.weatherforecaster.data.location.datasource

import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(intervalMillis: Long): Flow<Location>

    class LocationException(message: String) : Exception()

    companion object {
        const val DEFAULT_LOCATION_UPDATES_INTERVAL_MILLIS = 5000L
    }
}
