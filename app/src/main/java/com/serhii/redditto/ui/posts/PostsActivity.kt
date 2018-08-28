package com.serhii.redditto.ui.posts

import android.os.Bundle
import com.serhii.redditto.R
import com.serhii.redditto.core.extension.findFragmentById
import com.serhii.redditto.core.extension.replaceFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_posts.*
import javax.inject.Inject

class PostsActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var injectedFragment: PostsFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)
        setSupportActionBar(toolbar)

        findFragmentById(R.id.fragment_content)
                ?: replaceFragment(R.id.fragment_content, injectedFragment)
    }
}