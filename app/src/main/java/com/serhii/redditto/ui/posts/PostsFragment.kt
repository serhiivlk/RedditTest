package com.serhii.redditto.ui.posts

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.serhii.redditto.R
import com.serhii.redditto.core.di.ActivityScope
import com.serhii.redditto.core.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject

@ActivityScope
class PostsFragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PostsViewModel
    private val adapter = PostsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = obtainViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadPosts()
    }

    private fun initView() {
        posts_recycler.layoutManager = LinearLayoutManager(context)
        posts_recycler.adapter = adapter
        adapter.onItemClick {
            Toast.makeText(context, it.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadPosts() {
        viewModel.loadPosts()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.errorMessage.observe(this, Observer { error ->
            view?.let { Snackbar.make(it, error ?: "error", Snackbar.LENGTH_SHORT).show() }
        })
        viewModel.posts.observe(this, Observer {
            adapter.posts = it.orEmpty()
        })
    }

    private fun obtainViewModel(): PostsViewModel =
            ViewModelProviders.of(this, viewModelFactory)[PostsViewModel::class.java]
}