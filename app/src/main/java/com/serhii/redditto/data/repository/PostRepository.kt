package com.serhii.redditto.data.repository

import com.serhii.redditto.core.exception.ServerError
import com.serhii.redditto.core.model.Post
import com.serhii.redditto.data.remote.Failure
import com.serhii.redditto.data.remote.PostService
import com.serhii.redditto.data.remote.Result
import com.serhii.redditto.data.remote.Success
import com.serhii.redditto.data.remote.model.PostsResponse
import com.serhii.redditto.data.remote.model.toPosts
import retrofit2.Call
import javax.inject.Inject

class PostRepository @Inject constructor(private val service: PostService) {
    fun popular(): Result<List<Post>> {
        return request(service.popular()) { response: PostsResponse? ->
            return@request response?.toPosts()?.map { it.toPost() } ?: emptyList()
        }
    }

    private fun <T, R> request(call: Call<T>, transform: (T?) -> R): Result<R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Success(transform(response.body()))
                false -> Failure(ServerError())
            }
        } catch (e: Throwable) {
            Failure(e)
        }
    }
}