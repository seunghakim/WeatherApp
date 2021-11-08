package com.homework.weather.utilities

import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingConversions {
    @BindingAdapter("setHumidity")
    @JvmStatic
    fun setHumidity(textView: TextView, humidity: Float?) {
        humidity?.let {
            textView.text = String.format("%d%%",humidity.toInt())
        }
    }

    @BindingAdapter("setCurrentTemp")
    @JvmStatic
    fun setCurrentTemp(textView: TextView, currentTemp: Float?) {
        currentTemp?.let {
            textView.text = String.format("%d℃",currentTemp.toInt())
        }

    }


}