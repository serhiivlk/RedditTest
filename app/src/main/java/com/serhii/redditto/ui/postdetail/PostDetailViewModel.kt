package com.serhii.redditto.ui.postdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.serhii.redditto.core.interactor.GetPostUseCase
import com.serhii.redditto.core.model.Post
import com.serhii.redditto.data.remote.Failure
import com.serhii.redditto.data.remote.Success
import javax.inject.Inject

class PostDetailViewModel @Inject constructor(
        private val getPost: GetPostUseCase
) : ViewModel() {
    val title = MutableLiveData<String>()
    val thumbnail = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()

    fun loadDetail(permalink: String?) {
        if (permalink == null) return
        getPost.execute(GetPostUseCase.Params(permalink)) {
            when (it) {
                is Success -> handlePost(it.data)
                is Failure -> handleFailure(it.error)
            }
        }
    }

    private fun handlePost(post: Post) {
        title.value = post.title
        thumbnail.value = post.thumbnail
    }

    private fun handleFailure(throwable: Throwable) {
        errorMessage.value = throwable.message
    }

    override fun onCleared() {
        super.onCleared()
        thumbnail.value = null
        title.value = null
    }
}