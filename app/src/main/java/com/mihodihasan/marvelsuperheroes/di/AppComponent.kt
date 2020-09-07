package com.mihodihasan.marvelsuperheroes.di

import android.app.Application
import com.mihodihasan.marvelsuperheroes.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [/*AndroidInjectionModule::class, */AppModule::class, /*ActivityModule::class, */NetworkModule::class, /*RepositoryModule::class,*/ CoroutinesModule::class])
interface AppComponent {

    /*@Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }*/

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: Application)

    fun inject(activity: MainActivity)
}