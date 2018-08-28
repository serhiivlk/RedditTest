package com.serhii.redditto.ui.posts

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.serhii.redditto.R
import com.serhii.redditto.core.extension.inflate
import com.serhii.redditto.ui.model.PostView
import kotlinx.android.synthetic.main.list_item_post.view.*
import kotlin.properties.Delegates

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {
    var posts: List<PostView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    private var clickListener: (PostView) -> Unit = { _ -> }

    fun onItemClick(listener: (PostView) -> Unit) {
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = parent.inflate(R.layout.list_item_post)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position], clickListener)
    }

    override fun getItemCount(): Int = posts.size

    class PostViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: PostView, clickListener: (PostView) -> Unit) = with(itemView) {
            title.text = post.title

            setOnClickListener { clickListener(post) }
        }
    }
}