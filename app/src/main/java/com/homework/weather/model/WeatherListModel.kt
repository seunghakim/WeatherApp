package com.homework.weather.model


data class WeatherListModel (
    val todayWeather : WeatherModel,
    val tomorrowWeather : WeatherModel,
    val title : String
)
