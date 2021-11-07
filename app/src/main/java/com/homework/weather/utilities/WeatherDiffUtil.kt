package com.homework.weather.utilities

import androidx.recyclerview.widget.DiffUtil
import com.homework.weather.model.WeatherListModel

class WeatherDiffUtil(private val oldList: ArrayList<WeatherListModel>, private val newList: ArrayList<WeatherListModel>) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].woeid == newList[newItemPosition].woeid
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }

}