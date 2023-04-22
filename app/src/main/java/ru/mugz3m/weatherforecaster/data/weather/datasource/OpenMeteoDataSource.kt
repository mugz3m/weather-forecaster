package ru.mugz3m.weatherforecaster.data.weather.datasource

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import ru.mugz3m.weatherforecaster.data.weather.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.weather.model.WeekWeatherForecast

class OpenMeteoDataSource(retrofit: Retrofit) {
    private val service = retrofit.create(OpenMeteoApiService::class.java)

    fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        currentWeatherForecast: MutableLiveData<Result<CurrentWeatherForecast>>,
        showProgress: MutableLiveData<Boolean>
    ) {
        val call = service.getCurrentWeatherForecast(latitude, longitude)
        call.enqueue(object : Callback<CurrentWeatherForecast> {
            override fun onResponse(call: Call<CurrentWeatherForecast>, response: Response<CurrentWeatherForecast>) {
                if (response.code() == 200) {
                    currentWeatherForecast.value = Result.success(response.body()!!)
                }
                showProgress.value = false
            }

            override fun onFailure(call: Call<CurrentWeatherForecast>, throwable: Throwable) {
                currentWeatherForecast.value = Result.failure(throwable)
                showProgress.value = false
            }
        })
    }

    fun getWeekWeatherForecast(
        latitude: Double,
        longitude: Double,
        weekWeatherForecast: MutableLiveData<Result<WeekWeatherForecast>>,
        showProgress: MutableLiveData<Boolean>
    ) {
        val call = service.getWeekWeatherForecast(latitude, longitude)
        call.enqueue(object : Callback<WeekWeatherForecast> {
            override fun onResponse(call: Call<WeekWeatherForecast>, response: Response<WeekWeatherForecast>) {
                if (response.code() == 200) {
                    weekWeatherForecast.value = Result.success(response.body()!!)
                }
                showProgress.value = false
            }

            override fun onFailure(call: Call<WeekWeatherForecast>, throwable: Throwable) {
                weekWeatherForecast.value = Result.failure(throwable)
                showProgress.value = false
            }
        })
    }
}
