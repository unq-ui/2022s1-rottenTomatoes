package com.github.unqUi.model

class DraftMovie(
    var title: String,
    var description: String,
    var poster: String,
    var categories: MutableList<Category> = mutableListOf(),
    var relatedContent: MutableList<Movie> = mutableListOf()
)

class DraftCategory (var name: String)

class DraftUser(
    var name: String,
    var image: String,
    var email: String,
    var password: String,
)

class DraftReview(
    var userId: String,
    var movieId: String,
    var text: String,
    var stars: Int
)