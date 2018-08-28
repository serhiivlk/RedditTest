package com.serhii.redditto.data.remote.model

import com.serhii.redditto.core.model.Post

data class PostEntity(var title: String) {

    fun toPost(): Post = Post(title)
}