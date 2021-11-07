package com.homework.weather.di

import com.homework.weather.network.HomeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Singleton
    @Provides
    fun provideHomeApiService(): HomeApiService {
        return HomeApiService.create()
    }

}
