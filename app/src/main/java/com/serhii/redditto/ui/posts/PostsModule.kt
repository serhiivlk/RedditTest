package com.serhii.redditto.ui.posts

import com.serhii.redditto.core.di.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PostsModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun pstsFragment(): PostsFragment
}