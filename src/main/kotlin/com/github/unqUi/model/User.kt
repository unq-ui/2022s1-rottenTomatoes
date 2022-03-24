package com.github.unqUi.model

class User(
    val id: String,
    var name: String,
    var image: String,
    var email: String,
    var password: String,
    val reviews: MutableList<Review> = mutableListOf()
)