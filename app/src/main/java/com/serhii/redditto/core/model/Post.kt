package com.serhii.redditto.core.model

data class Post(val title: String, val thumbnail: String, val permalink: String) {
    companion object {
        @JvmStatic
        val EMPTY = Post("", "", "")
    }
}