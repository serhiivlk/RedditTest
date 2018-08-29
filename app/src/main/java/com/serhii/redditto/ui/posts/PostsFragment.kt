package com.serhii.redditto.ui.posts

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serhii.redditto.core.di.ActivityScope
import com.serhii.redditto.core.platform.BaseFragment
import com.serhii.redditto.databinding.FragmentPostsBinding
import com.serhii.redditto.ui.postdetail.postDetailIntent
import javax.inject.Inject

@ActivityScope
class PostsFragment @Inject constructor() : BaseFragment() {

    private val adapter = PostsAdapter()

    private lateinit var binding: FragmentPostsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPostsBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as PostsActivity).obtainViewModel()
            setLifecycleOwner(this@PostsFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadPosts()
    }

    private fun initView() {
        binding.postsRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PostsFragment.adapter
        }

        adapter.onItemClick {
            startActivity(context?.postDetailIntent(it))
        }
    }

    private fun loadPosts() {
        binding.viewModel?.loadPosts()
    }

}