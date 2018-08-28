package com.serhii.redditto.core.di

import com.serhii.redditto.ui.postdetail.PostDetailActivity
import com.serhii.redditto.ui.postdetail.PostDetailModule
import com.serhii.redditto.ui.posts.PostsActivity
import com.serhii.redditto.ui.posts.PostsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [PostsModule::class])
    abstract fun postsActivity(): PostsActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [PostDetailModule::class])
    abstract fun postDetailActivity(): PostDetailActivity
}