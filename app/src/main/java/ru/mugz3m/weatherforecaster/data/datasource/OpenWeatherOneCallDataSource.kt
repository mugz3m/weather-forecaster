package ru.mugz3m.weatherforecaster.data.datasource

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import ru.mugz3m.weatherforecaster.data.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.DailyWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.HourlyWeatherForecast

class OpenWeatherOneCallDataSource(private val retrofit: Retrofit) {
    suspend fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
        units: String,
        language: String,
        getCurrentWeatherForecastLiveData: MutableLiveData<CurrentWeatherForecast>
    ) {
        val service = retrofit.create(OpenWeatherOneCallApiService::class.java)
        val call = service.getCurrentWeatherForecast(
            latitude,
            longitude,
            exclude,
            apiKey,
            units,
            language
        )
        call.enqueue(object : Callback<CurrentWeatherForecast> {
            override fun onResponse(
                call: Call<CurrentWeatherForecast>,
                response: Response<CurrentWeatherForecast>
            ) {
                if (response.code() == 200) {
                    getCurrentWeatherForecastLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<CurrentWeatherForecast>, throwable: Throwable) = Unit
        })
    }

    suspend fun getHourlyWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
        units: String,
        language: String,
        getHourlyWeatherForecastLiveData: MutableLiveData<HourlyWeatherForecast>
    ) {
        val service = retrofit.create(OpenWeatherOneCallApiService::class.java)
        val call = service.getHourlyWeatherForecast(
            latitude,
            longitude,
            exclude,
            apiKey,
            units,
            language
        )
        call.enqueue(object : Callback<HourlyWeatherForecast> {
            override fun onResponse(
                call: Call<HourlyWeatherForecast>,
                response: Response<HourlyWeatherForecast>
            ) {
                if (response.code() == 200) {
                    getHourlyWeatherForecastLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<HourlyWeatherForecast>, throwable: Throwable) = Unit
        })
    }

    suspend fun getDailyWeatherForecast(
        latitude: Double,
        longitude: Double,
        exclude: String,
        apiKey: String,
        units: String,
        language: String,
        getDailyWeatherForecastLiveData: MutableLiveData<DailyWeatherForecast>
    ) {
        val service = retrofit.create(OpenWeatherOneCallApiService::class.java)
        val call = service.getDailyWeatherForecast(
            latitude,
            longitude,
            exclude,
            apiKey,
            units,
            language
        )
        call.enqueue(object : Callback<DailyWeatherForecast> {
            override fun onResponse(
                call: Call<DailyWeatherForecast>,
                response: Response<DailyWeatherForecast>
            ) {
                if (response.code() == 200) {
                    getDailyWeatherForecastLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<DailyWeatherForecast>, throwable: Throwable) = Unit
        })
    }
}
