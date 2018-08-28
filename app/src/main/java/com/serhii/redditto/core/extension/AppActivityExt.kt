package com.serhii.redditto.core.extension

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

fun AppCompatActivity.findFragmentById(id: Int): Fragment? =
        supportFragmentManager.findFragmentById(id)

fun AppCompatActivity.replaceFragment(id: Int, fragment: Fragment,
                                      block: (FragmentTransaction.() -> Unit)? = null) {
    supportFragmentManager.beginTransaction()
            .replace(id, fragment)
            .apply { block?.invoke(this) }
            .commit()
}

fun AppCompatActivity.setupToolbar(toolbar: Toolbar, block: ActionBar.() -> Unit) {
    setSupportActionBar(toolbar)
    supportActionBar?.apply(block)
}