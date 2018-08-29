package com.serhii.redditto.ui.posts

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.serhii.redditto.core.interactor.GetPostsUseCase
import com.serhii.redditto.core.model.Post
import com.serhii.redditto.ui.model.PostView
import com.serhii.redditto.ui.model.toView
import timber.log.Timber
import javax.inject.Inject

class PostsViewModel @Inject constructor(
        private val getPosts: GetPostsUseCase
) : ViewModel() {
    val posts = MutableLiveData<List<PostView>>().apply {
        value = emptyList()
    }
    val dataLoading = MutableLiveData<Boolean>()
    private var firstLoading = true

    fun loadPosts() {
        if (dataLoading.value == true) {
            return
        }
        dataLoading.value = firstLoading
        getPosts.execute(Unit) {
            firstLoading = false
            dataLoading.value = firstLoading
            it.determine(::handlePosts, ::handleFailure)
        }
    }

    private fun handlePosts(posts: List<Post>) {
        this.posts.value = posts.map { it.toView() }
    }

    private fun handleFailure(throwable: Throwable) {
        Timber.e(throwable)
    }

    override fun onCleared() {
        super.onCleared()
    }
}