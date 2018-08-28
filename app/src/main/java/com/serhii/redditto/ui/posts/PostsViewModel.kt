package com.serhii.redditto.ui.posts

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.serhii.redditto.core.interactor.GetPostsUseCase
import com.serhii.redditto.core.model.Post
import com.serhii.redditto.data.remote.Failure
import com.serhii.redditto.data.remote.Success
import com.serhii.redditto.ui.model.PostView
import com.serhii.redditto.ui.model.toView
import javax.inject.Inject

class PostsViewModel @Inject constructor(
        private val getPosts: GetPostsUseCase
) : ViewModel() {
    val posts = MutableLiveData<List<PostView>>()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun loadPosts() {
        getPosts.execute(Unit) {
            when (it) {
                is Success -> handlePosts(it.data)
                is Failure -> handleFailure(it.error)
            }
        }
    }

    private fun handlePosts(posts: List<Post>) {
        this.posts.value = posts.map { it.toView() }
    }

    private fun handleFailure(throwable: Throwable) {
        errorMessage.value = throwable.message ?: "Post load error"
    }

    override fun onCleared() {
        super.onCleared()
    }
}