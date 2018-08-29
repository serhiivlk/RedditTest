package com.serhii.redditto.ui.posts

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import com.serhii.redditto.ui.model.PostView

@BindingAdapter("posts")
fun setPosts(recycler: RecyclerView, posts: List<PostView>) {
    (recycler.adapter as PostsAdapter).posts = posts
}