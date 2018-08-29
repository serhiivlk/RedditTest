package com.serhii.redditto.ui.posts

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import com.serhii.redditto.R
import com.serhii.redditto.core.extension.findFragmentById
import com.serhii.redditto.core.extension.obtainViewModel
import com.serhii.redditto.core.extension.replaceFragment
import com.serhii.redditto.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostsActivity : BaseActivity() {

    @Inject
    lateinit var injectedFragment: PostsFragment
    @Inject
    lateinit var viewModalFactory: ViewModelProvider.Factory

    private lateinit var viewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        setSupportActionBar(toolbar)

        setupFragment()

        viewModel = obtainViewModel()
    }

    private fun setupFragment() {
        findFragmentById(R.id.fragment_content)
                ?: replaceFragment(R.id.fragment_content, injectedFragment)
    }

    fun obtainViewModel(): PostsViewModel = obtainViewModel(viewModalFactory) {}
}
