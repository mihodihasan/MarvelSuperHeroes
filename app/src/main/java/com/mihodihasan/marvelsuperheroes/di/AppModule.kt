package com.mihodihasan.marvelsuperheroes.di

import android.app.Application
import android.content.Context
import com.mihodihasan.marvelsuperheroes.network.ApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun getContext(application: Application): Context {
        return application
    }

    /*@Singleton
    @Provides
    fun providesScheduler(): BaseScheduler {
        return SchedulerProvider()
    }*/

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}
