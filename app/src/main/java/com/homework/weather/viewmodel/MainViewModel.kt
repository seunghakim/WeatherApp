package com.homework.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.weather.AppConstants
import com.homework.weather.model.LocationModel
import com.homework.weather.model.WeatherListModel
import com.homework.weather.model.WeatherModel
import com.homework.weather.model.repository.HomeRepository
import com.homework.weather.viewmodel.base.BaseViewModel
import com.homework.weather.viewmodel.event.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {
    private val _weatherListLiveData = MutableLiveData<ArrayList<WeatherListModel>>()
    val weatherListLiveData: LiveData<ArrayList<WeatherListModel>>
        get() = _weatherListLiveData


    private val _locationList: MutableLiveData<ArrayList<LocationModel>> = MutableLiveData()
    val locationList: LiveData<ArrayList<LocationModel>>
        get() = _locationList

    suspend fun requestLocationList() {
        val response = homeRepository.requestLocationList("se")
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                _locationList.postValue(it)
            }
        } else {
            viewEvent(AppConstants.NETWORK_CALL_FAIL)
        }
    }

    suspend fun requestWeatherList(woeid: Int?) {
        woeid?.let { id ->
            val response = homeRepository.requestWeatherData(id)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val list = arrayListOf<WeatherListModel>()
                    Log.d("seungha", "it.title = " + it.title)
                    list.add(
                        WeatherListModel(
                            it.consolidated_weather[0],
                            it.consolidated_weather[1],
                            it.title,
                            it.woeid
                        )
                    )
                    _weatherListLiveData.postValue(list)

                }

            } else {
                viewEvent(AppConstants.NETWORK_CALL_FAIL)
            }
        }
    }
}