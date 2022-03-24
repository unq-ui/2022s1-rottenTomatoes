package com.github.unqUi.model

import java.util.regex.Pattern

val emailPattern: Pattern = Pattern.compile(
"[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
"\\@" +
"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
"(" +
"\\." +
"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
")+"
);

class RottenTomatoesSystem {
    val movies: MutableList<Movie> = mutableListOf()
    val categories: MutableList<Category> = mutableListOf()
    val users: MutableList<User> = mutableListOf()
    val reviews: MutableList<Review> = mutableListOf()
    private val idGenerator = IdGenerator()

    fun addMovie(movie: DraftMovie): Movie {
        validateMovie(movie)
        val newMovie = Movie(idGenerator.nextMovieId(), movie.title, movie.description, movie.poster, movie.categories, movie.relatedContent)
        movies.add(newMovie)
        return newMovie
    }

    private fun validateMovie(movie: DraftMovie) {
        if (movie.title.isBlank()) throw MovieError("Title is empty")
        if (movie.description.isBlank()) throw MovieError("Description is empty")
        if (movie.poster.isBlank()) throw MovieError("Poster is empty")
        if (movies.any { it.title == movie.title }) throw MovieError("Exist another movie with same title")
    }

    fun addCategory(category: DraftCategory): Category {
        validateCategory(category)
        val newCategory = Category(idGenerator.nextCategoryId(), category.name)
        categories.add(newCategory)
        return newCategory
    }

    private fun validateCategory(category: DraftCategory) {
        if (category.name.isBlank()) throw CategoryError("Name is empty")
        if (categories.any { it.name == category.name }) throw CategoryError("Exist another category with same name")
    }

    fun addUser(user: DraftUser): User {
        validateUser(user)
        val newUser = User(idGenerator.nextUserId(), user.name, user.image, user.email, user.password)
        users.add(newUser)
        return newUser
    }

    private fun validateUser(user: DraftUser) {
        if (user.name.isBlank()) throw UserError("Name is empty")
        if (user.image.isBlank()) throw UserError("Image is empty")
        if (user.email.isBlank()) throw UserError("Email is empty")
        if (!emailPattern.matcher(user.email).matches()) throw UserError("Email is invalid")
        if (user.password.isBlank()) throw UserError("Password is empty")
        if (users.any { it.email == user.email }) throw UserError("Exist another user with same email")
    }

    fun addReview(review: DraftReview): Review {
        validateReview(review)
        val user = getUserById(review.userId)
        val movie = getMovieById(review.movieId)
        val newReview = Review(idGenerator.nextReviewId(), user, movie, review.text, review.stars)
        user.reviews.add(newReview)
        reviews.add(newReview)
        return newReview
    }

    private fun validateReview(review: DraftReview) {
        validateUserId(review.userId)
        validateMovieId(review.movieId)
        if (review.text.isBlank()) throw ReviewError("Content is empty")
        if (review.stars !in 0..5) throw ReviewError("Stars out of range")
        if (reviews.any { it.user.id == review.userId && it.movie.id == review.movieId }) throw  ReviewError("User already have a review")
    }

    fun getUserById(userId: String): User {
        validateUserId(userId)
        return users.find { it.id == userId } ?: throw UserError("User not found")
    }

    fun getMovieById(movieId: String): Movie {
        validateMovieId(movieId)
        return movies.find { it.id == movieId } ?: throw MovieError("Movie not found")
    }

    private fun validateUserId(userId: String) {
        if (userId.isBlank()) throw UserError("UserId is empty")
    }

    private fun validateMovieId(movieId: String) {
        if (movieId.isBlank()) throw MovieError("MovieId is empty")
    }
}