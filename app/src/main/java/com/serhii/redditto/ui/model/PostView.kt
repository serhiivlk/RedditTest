package com.serhii.redditto.ui.model

import com.serhii.redditto.core.model.Post

data class PostView(val title: String, val thumbnail: String, val permalink: String)

fun Post.toView(): PostView =
        PostView(
                title,
                thumbnail,
                permalink
        )