package com.homework.weather.viewmodel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.homework.weather.viewmodel.event.Event

open class BaseViewModel : ViewModel(){
    //이벤트 발행을 위한 라이브 데이터
    private val _viewEvent = MutableLiveData<Event<Any>>()
    val viewEvent: LiveData<Event<Any>>
        get() = _viewEvent

    fun viewEvent(content: Any) {
        _viewEvent.value = Event(content)
    }
}