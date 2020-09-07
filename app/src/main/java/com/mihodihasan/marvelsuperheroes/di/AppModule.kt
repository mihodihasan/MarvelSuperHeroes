package com.mihodihasan.marvelsuperheroes.di

import android.app.Application
import android.content.Context
import com.mihodihasan.marvelsuperheroes.common.UseCaseHandler
import com.mihodihasan.marvelsuperheroes.common.UseCaseScheduler
import com.mihodihasan.marvelsuperheroes.common.UseCaseThreadPoolScheduler
import com.mihodihasan.marvelsuperheroes.main.MainContract
import com.mihodihasan.marvelsuperheroes.main.MainPresenter
import com.mihodihasan.marvelsuperheroes.main.usecase.GetHeroList
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
    @Singleton
    @Provides
    fun providesUseCaseThreadPoolScheduler(useCaseThreadPoolScheduler: UseCaseThreadPoolScheduler): UseCaseScheduler {
        return UseCaseThreadPoolScheduler()
    }

    @Singleton
    @Provides
    fun providesMainPresenter(mUseCaseHandler: UseCaseHandler, getHeroList: GetHeroList): MainContract.Presenter {
        return MainPresenter(mUseCaseHandler, getHeroList)
    }



}
