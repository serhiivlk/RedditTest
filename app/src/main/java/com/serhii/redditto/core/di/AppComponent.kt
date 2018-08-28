package com.serhii.redditto.core.di

import android.app.Application
import com.serhii.redditto.core.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dagger.android.support.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    ApiModule::class,
    ActivityBindingModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}