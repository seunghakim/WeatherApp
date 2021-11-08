package com.homework.weather.model.repository

import com.homework.weather.model.LocationModel
import com.homework.weather.model.response.ResponseWeatherData
import com.homework.weather.network.HomeApiService
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val service : HomeApiService) {

    suspend fun requestLocationList(id : String) : Response<ArrayList<LocationModel>> {
        return service.getLocationList(id)
    }

    suspend fun requestWeatherData(woeid : Int) : Response<ResponseWeatherData> {
        return service.getWeatherData(woeid)
    }

}