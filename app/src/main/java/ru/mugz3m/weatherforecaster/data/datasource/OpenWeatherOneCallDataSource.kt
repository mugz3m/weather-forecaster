package ru.mugz3m.weatherforecaster.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import ru.mugz3m.weatherforecaster.data.model.CurrentWeatherForecast
import ru.mugz3m.weatherforecaster.data.model.FiveDayWeatherForecast

class OpenWeatherOneCallDataSource(private val retrofit: Retrofit) {
    suspend fun getCurrentWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
        language: String,
        getCurrentWeatherForecastLiveData: MutableLiveData<CurrentWeatherForecast>
    ) {
        val service = retrofit.create(OpenWeatherOneCallApiService::class.java)
        val call = service.getCurrentWeatherForecast(
            latitude,
            longitude,
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

            override fun onFailure(call: Call<CurrentWeatherForecast>, throwable: Throwable) {
                Log.d("OPEN_WEATHER_ONE_CALL_DATA_SOURCE", throwable.message.toString())
            }
        })
    }

    suspend fun getFiveDayWeatherForecast(
        latitude: Double,
        longitude: Double,
        apiKey: String,
        units: String,
        language: String,
        getFiveDayWeatherForecastLiveData: MutableLiveData<FiveDayWeatherForecast>
    ) {
        val service = retrofit.create(OpenWeatherOneCallApiService::class.java)
        val call = service.getFiveDayWeatherForecast(
            latitude,
            longitude,
            apiKey,
            units,
            language
        )
        call.enqueue(object : Callback<FiveDayWeatherForecast> {
            override fun onResponse(
                call: Call<FiveDayWeatherForecast>,
                response: Response<FiveDayWeatherForecast>
            ) {
                if (response.code() == 200) {
                    getFiveDayWeatherForecastLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<FiveDayWeatherForecast>, throwable: Throwable) {
                Log.d("OPEN_WEATHER_ONE_CALL_DATA_SOURCE", throwable.message.toString())
            }
        })
    }
}
