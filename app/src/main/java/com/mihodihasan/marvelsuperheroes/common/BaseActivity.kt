package com.mihodihasan.marvelsuperheroes.common

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import timber.log.Timber

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        Timber.d("Timber is Running in BaseActivity")
    }

//    override fun attachBaseContext(newBase: Context?) {
//        super.attachBaseContext(newBase?.let { ViewPumpContextWrapper.wrap(it) })
//    }
}