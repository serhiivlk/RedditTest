package com.serhii.redditto.core.interactor

import com.serhii.redditto.core.model.Post
import com.serhii.redditto.data.remote.Result
import com.serhii.redditto.data.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
        private val postsRepository: PostRepository
) : UseCase<GetPostUseCase.Params, Post>() {
    override suspend fun executeOnBackground(params: Params): Result<Post> {
        return postsRepository.postByPermalink(params.permalink)
    }

    class Params(val permalink: String)
}