package com.serhii.redditto.core.interactor

import com.serhii.redditto.core.model.Post
import com.serhii.redditto.data.remote.Result
import com.serhii.redditto.data.repository.PostRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
        private val postRepository: PostRepository
) : UseCase<List<Post>>() {
    override suspend fun executeOnBackground(): Result<List<Post>> {
        return postRepository.popular()
    }
}