package com.serhii.redditto.ui.posts

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.serhii.redditto.core.interactor.GetPostsUseCase
import com.serhii.redditto.core.model.Post
import com.serhii.redditto.ui.model.PostView
import com.serhii.redditto.ui.model.toView
import kotlinx.coroutines.experimental.Job
import timber.log.Timber
import javax.inject.Inject

class PostsViewModel @Inject constructor(
        private val getPosts: GetPostsUseCase
) : ViewModel() {
    val posts = MutableLiveData<List<PostView>>().apply {
        value = emptyList()
    }
    val dataLoading = MutableLiveData<Boolean>()
    val isFailure = MutableLiveData<Boolean>()
    private var firstLoading = true
    private var job: Job? = null

    fun loadPosts() {
        if (job?.isActive == true) {
            return
        }
        dataLoading.value = firstLoading || (isFailure.value == true)
        isFailure.value = false
        job = getPosts.execute(Unit) {
            firstLoading = false
            dataLoading.value = false
            it.determine(::handlePosts, ::handleFailure)
        }
    }

    private fun handlePosts(posts: List<Post>) {
        this.posts.value = posts.map { it.toView() }
    }

    private fun handleFailure(throwable: Throwable) {
        Timber.e(throwable)
        isFailure.value = true
    }

    override fun onCleared() {
        super.onCleared()
    }
}