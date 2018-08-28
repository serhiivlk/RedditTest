package com.serhii.redditto.data.remote.model

import com.serhii.redditto.core.model.Post

data class PostEntity(var title: String, var thumbnail: String, val permalink: String) {

    fun toPost(): Post = Post(title, thumbnail, permalink)
}