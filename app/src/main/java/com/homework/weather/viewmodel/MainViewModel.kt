package com.homework.weather.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.homework.weather.AppConstants
import com.homework.weather.model.LocationModel
import com.homework.weather.model.WeatherListModel
import com.homework.weather.model.repository.HomeRepository
import com.homework.weather.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {
    //날씨리스트를 담을 liveData
    private val _weatherListLiveData = MutableLiveData<ArrayList<WeatherListModel>>()
    val weatherListLiveData: LiveData<ArrayList<WeatherListModel>>
        get() = _weatherListLiveData
    //지역 데이터를 담을 liveData
    private val _locationList: MutableLiveData<ArrayList<LocationModel>> = MutableLiveData()
    val locationList: LiveData<ArrayList<LocationModel>>
        get() = _locationList

    //LocationList api 호출
    suspend fun requestLocationList() {
        val response = homeRepository.requestLocationList("se")
        if (response.isSuccessful) {
            val body = response.body()
            body?.let {
                _locationList.postValue(it)
            }
        } else {
            //네트워크 호출이 실패시 에러처리..
            viewEvent(AppConstants.NETWORK_CALL_FAIL)
        }
    }

    //날씨 데이터 api 호출.
    suspend fun requestWeatherList(woeid: Int?) {
        woeid?.let { id ->
            val response = homeRepository.requestWeatherData(id)
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    val list = arrayListOf<WeatherListModel>()
                    //오늘의 날씨와 내일의 날씨,타이틀등을 담어 새로운 listModel로 설정.
                    list.add(
                        WeatherListModel(
                            it.consolidated_weather[0],
                            it.consolidated_weather[1],
                            it.title
                        )
                    )
                    _weatherListLiveData.postValue(list)
                }
            } else {
                //네트워크 호출이 실패시 에러처리..
                viewEvent(AppConstants.NETWORK_CALL_FAIL)
            }
        }
    }
}