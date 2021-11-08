package com.homework.weather

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import dagger.hilt.android.HiltAndroidApp


//Hilt 사용을 위한 설정
@HiltAndroidApp
class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        initLogger()
    }

    private fun initLogger() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)
            .methodCount(0)
            .methodOffset(7)
            .tag("TEST")
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    init{
        instance = this
    }

    companion object {
        lateinit var instance: AppApplication
        fun getContext() : Context {
            return instance.applicationContext
        }
    }
}