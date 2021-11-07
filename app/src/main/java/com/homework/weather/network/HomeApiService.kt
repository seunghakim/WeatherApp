package com.homework.weather.network

import com.homework.weather.model.LocationModel
import com.homework.weather.model.WeatherModel
import com.homework.weather.model.response.ResponseWeatherData
import com.homework.weather.network.utils.ServiceUtils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HomeApiService {

    //se 포함된 리스트 api 호출
    @GET("api/location/search")
    suspend fun getLocationList(
        @Query("query") id: String
    ): Response<ArrayList<LocationModel>>

    //get weather Data
    @GET("api/location/{woeid}")
    suspend fun getWeatherData(
        @Path("woeid") woeid: Int): Response<ResponseWeatherData>

    companion object {
        fun create(): HomeApiService {
          return  ServiceUtils.getInstance().getService(HomeApiService::class.java)
        }
    }
}