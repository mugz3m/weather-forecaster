package ru.mugz3m.weatherforecaster.ui.stateholders

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mugz3m.weatherforecaster.data.location.repository.LocationRepository

class LocationViewModel(private val repository: LocationRepository) : ViewModel() {
    fun initLocationClient() {
        repository.initLocationClient()
    }

    fun subscribeToLocationUpdates() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.subscribeToLocationUpdates()
            }
        }
    }

    fun cancelLocationScopeJobs() {
        repository.cancelLocationScopeJobs()
    }
}
