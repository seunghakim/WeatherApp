package com.homework.weather.model

import java.util.*

data class WeatherModel(
    val id: Long?,
    val weather_state_name: String?,
    val weather_state_abbr: String?,
    val wind_direction_compass: String?,
    val created: String?,
    val applicable_date: Date?,
    val min_temp: Float?,
    val max_temp: Float?,
    val the_temp: Float?,
    val wind_direction: Float?,
    val air_pressure: Float?,
    val humidity: Float?,
    val visibility: Float?,
    val predictability: Int?
)
