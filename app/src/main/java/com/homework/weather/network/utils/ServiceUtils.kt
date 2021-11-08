package com.homework.weather.network.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.homework.weather.AppConstants
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class ServiceUtils private constructor() {

    private var gson: Gson = GsonBuilder().create()

    private var retrofit: Retrofit? = null
    private fun getRetrofit(): Retrofit {
        //response데이터 로그를 위한 Interceptor 설정
        val loggingInterceptor = HttpLoggingInterceptor { message: String? ->
            Logger.d(message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(AppConstants.HTTP_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(AppConstants.HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(AppConstants.HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build()
        retrofit = Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .baseUrl(AppConstants.BASE_URL)
            .build()

        return retrofit!!
    }


    fun <T> getService(service: Class<T>): T {
        return getRetrofit().create(service)
    }

    companion object {
        private var instance: ServiceUtils? = null

        @Synchronized
        fun getInstance(): ServiceUtils {
            instance = ServiceUtils()

            return instance!!
        }

    }

}
