package com.serhii.redditto.ui.postdetail

import com.serhii.redditto.core.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PostDetailModule {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun postDetailFragment(): PostDetailFragment
}