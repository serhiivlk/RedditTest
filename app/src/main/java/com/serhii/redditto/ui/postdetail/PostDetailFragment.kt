package com.serhii.redditto.ui.postdetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.serhii.redditto.core.di.ActivityScope
import com.serhii.redditto.core.platform.BaseFragment
import com.serhii.redditto.databinding.FragmentPostDetailBinding
import javax.inject.Inject

fun PostDetailFragment.putPermalink(permalink: String): PostDetailFragment = apply {
    arguments = Bundle().apply { putString(PostDetailFragment.EXTRA_POST_PERMALINK, permalink) }
}

@ActivityScope
class PostDetailFragment @Inject constructor() : BaseFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentPostDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPostDetailBinding.inflate(inflater, container, false)

        binding.viewModel = obtainViewModel()
        binding.setLifecycleOwner(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel?.loadDetail(arguments?.getString(EXTRA_POST_PERMALINK))
    }

    fun obtainViewModel(): PostDetailViewModel =
            ViewModelProviders.of(this, viewModelFactory)[PostDetailViewModel::class.java]

    companion object {
        const val EXTRA_POST_PERMALINK = "post_permalink"
    }
}