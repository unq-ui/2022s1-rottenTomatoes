package com.github.unqUi.model

class Movie(
    val id: String,
    var title: String,
    var description: String,
    var poster: String,
    var categories: MutableList<Category>,
    var relatedContent: MutableList<Movie>
)