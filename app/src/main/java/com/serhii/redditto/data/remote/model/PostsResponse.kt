package com.serhii.redditto.data.remote.model

data class PostsResponse(
        var kind: String,
        var data: PostsData
)

data class PostsData(
        var children: List<PostItem>
)

data class PostItem(
        var data: PostEntity
)

fun PostsResponse.toPosts(): List<PostEntity> =
        data.children.map { it.data }
