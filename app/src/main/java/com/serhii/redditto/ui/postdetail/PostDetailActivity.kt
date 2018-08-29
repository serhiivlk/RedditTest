package com.serhii.redditto.ui.postdetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.serhii.redditto.R
import com.serhii.redditto.core.extension.findFragmentById
import com.serhii.redditto.core.extension.replaceFragment
import com.serhii.redditto.core.extension.setupToolbar
import com.serhii.redditto.core.platform.BaseActivity
import kotlinx.android.synthetic.main.activity_post_detail.*
import javax.inject.Inject

fun Context.postDetailIntent(permalink: String) =
        Intent(this, PostDetailActivity::class.java).apply {
            putExtra(PostDetailFragment.EXTRA_POST_PERMALINK, permalink)
        }

class PostDetailActivity : BaseActivity() {
    @Inject
    lateinit var injectedFragment: PostDetailFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)
        setupToolbar(toolbar) {
            setDisplayHomeAsUpEnabled(true)
        }

        findFragmentById(R.id.fragment_content)
                ?: replaceFragment(R.id.fragment_content, postDetailFragment())
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun postDetailFragment() = injectedFragment
            .putPermalink(intent.getStringExtra(PostDetailFragment.EXTRA_POST_PERMALINK))
}