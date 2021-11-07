package com.homework.weather.ui.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.homework.weather.AppConstants
import com.homework.weather.databinding.ListItemWeatherBinding
import com.homework.weather.model.WeatherListModel
import com.homework.weather.utilities.ImageUtils

class WeatherRecyclerViewHolder(private val binding: ListItemWeatherBinding) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: WeatherListModel) {
        binding.item = model

        val todayImage = String.format(AppConstants.IMAGE_URL,model.todayWeather.weather_state_abbr)
        val tomorrowImage = String.format(AppConstants.IMAGE_URL,model.tomorrowWeather.weather_state_abbr)

        ImageUtils.urlLoadImage(todayImage).into(binding.ivTodayWeather)
        ImageUtils.urlLoadImage(tomorrowImage).into(binding.ivTomorrowWeather)

        binding.executePendingBindings()

    }
}