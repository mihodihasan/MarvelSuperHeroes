package com.mihodihasan.marvelsuperheroes

import android.app.Application
import com.mihodihasan.marvelsuperheroes.di.AppComponent
import com.mihodihasan.marvelsuperheroes.di.DaggerAppComponent

class HeroApp : Application() {
    val appComponent: AppComponent by lazy {
//        DaggerAppComponent.factory().create(applicationContext)
        DaggerAppComponent.builder().application(this).build()
    }
    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}