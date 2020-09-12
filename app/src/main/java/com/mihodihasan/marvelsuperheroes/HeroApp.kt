package com.mihodihasan.marvelsuperheroes

import android.app.Application
import com.mihodihasan.marvelsuperheroes.di.AppComponent
import com.mihodihasan.marvelsuperheroes.di.DaggerAppComponent
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

class HeroApp : Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }
    override fun onCreate() {
        super.onCreate()
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/lato-reg.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + ": line-number " + element.lineNumber
                }
            })
        }

        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}