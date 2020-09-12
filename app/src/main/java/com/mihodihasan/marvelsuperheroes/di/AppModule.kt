package com.mihodihasan.marvelsuperheroes.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.mihodihasan.marvelsuperheroes.common.UseCaseHandler
import com.mihodihasan.marvelsuperheroes.common.UseCaseScheduler
import com.mihodihasan.marvelsuperheroes.common.UseCaseThreadPoolScheduler
import com.mihodihasan.marvelsuperheroes.main.MainContract
import com.mihodihasan.marvelsuperheroes.main.MainPresenter
import com.mihodihasan.marvelsuperheroes.main.usecase.GetComicsList
import com.mihodihasan.marvelsuperheroes.main.usecase.GetHeroList
import com.mihodihasan.marvelsuperheroes.network.ApiInterface
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun getContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun providesApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
    @Singleton
    @Provides
    fun providesUseCaseThreadPoolScheduler(mThreadPoolExecutor: ThreadPoolExecutor): UseCaseScheduler {
        return UseCaseThreadPoolScheduler(mThreadPoolExecutor)
    }

    @Singleton
    @Provides
    fun providesMainPresenter(mUseCaseHandler: UseCaseHandler, getHeroList: GetHeroList, getComicsList: GetComicsList): MainContract.Presenter {
        return MainPresenter(mUseCaseHandler, getHeroList, getComicsList)
    }

 @Singleton
    @Provides
    fun providesThreadPoolExecutor(): ThreadPoolExecutor {
        return ThreadPoolExecutor(
            2, 4,
            30.toLong(),
            TimeUnit.SECONDS, ArrayBlockingQueue(2)
        )
    }

    @Singleton
    @Provides
    fun providesSharedPreferencesEditor(context:Context): SharedPreferences {
        return context.getSharedPreferences(
            "MARVEL_HERO_PREFS",
            Context.MODE_PRIVATE
        )
    }
}
