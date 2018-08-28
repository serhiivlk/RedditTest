package com.serhii.redditto.data.remote

import com.serhii.redditto.data.remote.model.PostsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostService @Inject constructor(retrofit: Retrofit) : PostApi {
    private val api by lazy {
        retrofit.create(PostApi::class.java)
    }

    override fun popular(): Call<PostsResponse> = api.popular()

}

interface PostApi {
    @GET("popular.json")
    fun popular(): Call<PostsResponse>
}
