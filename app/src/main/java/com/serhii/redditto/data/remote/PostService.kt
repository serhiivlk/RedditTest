package com.serhii.redditto.data.remote

import com.serhii.redditto.data.remote.model.PostsResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostService @Inject constructor(retrofit: Retrofit) : PostApi {
    private val api by lazy {
        retrofit.create(PostApi::class.java)
    }

    override fun postDetail(permalink: String): Call<List<PostsResponse>> {
        val link = permalink.dropLastWhile { it == '/' }
                .dropWhile { it == '/' }
        return api.postDetail(link)
    }


    override fun popular(): Call<PostsResponse> = api.popular()

}

interface PostApi {
    @GET("r/popular.json")
    fun popular(): Call<PostsResponse>

    @GET("{permalink}.json")
    fun postDetail(@Path("permalink") permalink: String): Call<List<PostsResponse>>
}
