package com.github.unqUi.model

class IdGenerator {
    private var currentUserId = 0
    private var currentMovieId = 0
    private var currentCategoryId = 0
    private var currentReviewId = 0

    fun nextUserId(): String = "u_${++currentUserId}"
    fun nextMovieId(): String = "mov_${++currentMovieId}"
    fun nextCategoryId(): String = "cat_${++currentCategoryId}"
    fun nextReviewId(): String = "rev_${++currentReviewId}"
}