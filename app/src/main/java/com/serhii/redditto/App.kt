package com.serhii.redditto

import com.serhii.redditto.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
                .app(this)
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        initLibraries()
    }

    private fun initLibraries() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}