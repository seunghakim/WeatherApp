package com.homework.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.homework.weather.AppConstants
import com.homework.weather.R
import com.homework.weather.databinding.ActivityMainBinding
import com.homework.weather.model.WeatherListModel
import com.homework.weather.ui.adapter.recycleradapter.MainWeatherRecyclerAdapter
import com.homework.weather.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private var adapter: MainWeatherRecyclerAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
        }

        setContentView(binding.root)

        initUI()
        initViewModel()
        initViewModelEvent()
        requestLocationList()

    }

    private fun initUI() {
        with(binding) {
            adapter = MainWeatherRecyclerAdapter()
            rvWeather.adapter = adapter
            swipeRefresh.setOnRefreshListener(this@MainActivity)
        }

    }

    private fun initViewModel() {
        with(viewModel) {
            locationList.observe(this@MainActivity, Observer {
                it.forEach { locationModel ->
                    lifecycleScope.launch {
                        viewModel.requestWeatherList(locationModel.woeid)
                    }
                }
            })
            weatherListLiveData.observe(this@MainActivity, Observer {
                //로딩바 처리
                if (binding.pbLoading.visibility == View.VISIBLE) {
                    binding.pbLoading.visibility = View.GONE
                }
                //새로 고침 처리
                binding.swipeRefresh.isRefreshing = false

                if (binding.layoutLoading.visibility == View.GONE) {
                    binding.layoutLoading.visibility = View.VISIBLE
                }
                adapter?.addListData(it)

            })
        }
    }

    private fun initViewModelEvent(){
        with(viewModel) {
            //서버 에러 처리.
            viewEvent.observe(this@MainActivity, Observer {
                it.getContentIfNotHandled()?.let { event ->
                    when (event) {
                        AppConstants.NETWORK_CALL_FAIL -> {
                            binding.swipeRefresh.isRefreshing = false
                            Toast.makeText(applicationContext, resources.getString(R.string.network_call_fail_toast), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun requestLocationList() {
        lifecycleScope.launch {
            viewModel.requestLocationList()
        }
    }

    //새로고침
    override fun onRefresh() {
        lifecycleScope.launch {
            adapter?.clearData()
            binding.layoutLoading.visibility = View.GONE
            requestLocationList()

        }
    }

}