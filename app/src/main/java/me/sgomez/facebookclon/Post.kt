package me.sgomez.facebookclon

data class Post(
    val id: Int,
    val author: String,
    val time: String,
    val content: String,
    val likes: Int = 0,
    val comments: Int = 0,
    val liked: Boolean = false
)
