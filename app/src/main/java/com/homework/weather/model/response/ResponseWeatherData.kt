package com.homework.weather.model.response

import com.homework.weather.model.WeatherModel

data class ResponseWeatherData(
    val consolidated_weather: ArrayList<WeatherModel> = arrayListOf(),
    val title: String,
    val woeid : Int,
)
