package com.homework.weather.ui.adapter.recycleradapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.homework.weather.R
import com.homework.weather.model.WeatherListModel
import com.homework.weather.ui.adapter.viewholder.WeatherRecyclerViewHolder
import java.util.*

class MainWeatherRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var weatherList = arrayListOf<WeatherListModel>()

    fun addListData(weatherList : ArrayList<WeatherListModel>){
        this.weatherList.addAll(weatherList)
        notifyDataSetChanged()
    }

    fun clearData(){
        this.weatherList.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WeatherRecyclerViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
            R.layout.list_item_weather, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as WeatherRecyclerViewHolder
        viewHolder.onBind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }
}