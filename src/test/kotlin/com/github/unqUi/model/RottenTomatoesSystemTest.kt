package com.github.unqUi.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RottenTomatoesSystemTest {
    private val draftMovie = DraftMovie("mov1", "mov1", "mov1")
    private val draftCategory = DraftCategory("cat")
    private val draftUser = DraftUser("name", "image", "email@gmail.com", "password")
    private val draftReview = DraftReview("u_1", "mov_1", "Text", 3)

    @Test
    fun addMovie() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        rottenTomatoesSystem.addMovie(draftMovie)
        assertEquals(rottenTomatoesSystem.movies.size, 1)
        val movie = rottenTomatoesSystem.movies[0]
        assertEquals(movie.id, "mov_1")
        assertEquals(movie.title, "mov1")
        assertEquals(movie.description, "mov1")
        assertEquals(movie.poster, "mov1")
    }

    @Test
    fun addMovieTwoTimes() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        rottenTomatoesSystem.addMovie(draftMovie)
        assertFailsWith<MovieError>("Exist another movie with same title") {
            rottenTomatoesSystem.addMovie(draftMovie)
        }
    }

    @Test
    fun addMovieWithoutTitle() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftMovie = DraftMovie("", "mov1", "mov1")
        assertFailsWith<MovieError>("Title is empty") {
            rottenTomatoesSystem.addMovie(customDraftMovie)
        }
    }

    @Test
    fun addMovieWithoutDescription() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftMovie = DraftMovie("t1", "", "mov1")
        assertFailsWith<MovieError>("Description is empty") {
            rottenTomatoesSystem.addMovie(customDraftMovie)
        }
    }

    @Test
    fun addMovieWithoutPoster() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftMovie = DraftMovie("t1", "desc", "")
        assertFailsWith<MovieError>("Poster is empty") {
            rottenTomatoesSystem.addMovie(customDraftMovie)
        }
    }

    @Test
    fun editMovie() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        val originalMovie = rottenTomatoesSystem.getMovieById("mov_1")
        assertEquals(originalMovie.id, "mov_1")
        assertEquals(originalMovie.title, "mov1")
        assertEquals(originalMovie.description, "mov1")
        assertEquals(originalMovie.poster, "mov1")

        rottenTomatoesSystem.editMovie("mov_1", DraftMovie("edit", "edit", "edit"))

        val editedMovie = rottenTomatoesSystem.getMovieById("mov_1")
        assertEquals(editedMovie.id, "mov_1")
        assertEquals(editedMovie.title, "edit")
        assertEquals(editedMovie.description, "edit")
        assertEquals(editedMovie.poster, "edit")
    }

    @Test
    fun editMovieWithSameName() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        val originalMovie = rottenTomatoesSystem.getMovieById("mov_1")
        assertEquals(originalMovie.id, "mov_1")
        assertEquals(originalMovie.title, "mov1")
        assertEquals(originalMovie.description, "mov1")
        assertEquals(originalMovie.poster, "mov1")

        rottenTomatoesSystem.editMovie("mov_1", DraftMovie("mov1", "edit", "edit"))

        val editedMovie = rottenTomatoesSystem.getMovieById("mov_1")
        assertEquals(editedMovie.id, "mov_1")
        assertEquals(editedMovie.title, "mov1")
        assertEquals(editedMovie.description, "edit")
        assertEquals(editedMovie.poster, "edit")
    }

    @Test
    fun editMovieWithRepeatedName() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        rottenTomatoesSystem.addMovie(DraftMovie("mov2", "mov2", "mov2"))
        assertFailsWith<MovieError>("Exist another movie with same name") {
            rottenTomatoesSystem.editMovie("mov_1", DraftMovie("mov2", "edit", "edit"))
        }
    }

    @Test
    fun editMovieWithMovieIdNotFound() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        assertFailsWith<MovieError>("Movie not found") {
            rottenTomatoesSystem.editMovie("mov_2", DraftMovie("mov2", "edit", "edit"))
        }
    }

    @Test
    fun addCategory() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.categories.size, 0)
        rottenTomatoesSystem.addCategory(draftCategory)
        assertEquals(rottenTomatoesSystem.categories.size, 1)
        val category = rottenTomatoesSystem.categories[0]
        assertEquals(category.id, "cat_1")
        assertEquals(category.name, "cat")
    }

    @Test
    fun addCategoryTwoTimes() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.categories.size, 0)
        rottenTomatoesSystem.addCategory(draftCategory)
        assertFailsWith<CategoryError>("Exist another category with same name") {
            rottenTomatoesSystem.addCategory(draftCategory)
        }
    }

    @Test
    fun addCategoryWithoutName() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.categories.size, 0)
        val customDraftCategory = DraftCategory("")
        assertFailsWith<CategoryError>("Name is empty") {
            rottenTomatoesSystem.addCategory(customDraftCategory)
        }
    }

    @Test
    fun editCategory() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addCategory(draftCategory)

        val originalCategory = rottenTomatoesSystem.getCategoryById("cat_1")
        assertEquals(originalCategory.id, "cat_1")
        assertEquals(originalCategory.name, "cat")

        rottenTomatoesSystem.editCategory("cat_1", DraftCategory("anotherCat"))

        val editedCategory = rottenTomatoesSystem.getCategoryById("cat_1")
        assertEquals(editedCategory.id, "cat_1")
        assertEquals(editedCategory.name, "anotherCat")
    }

    @Test
    fun editCategoryWithRepeatedName() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addCategory(draftCategory)
        rottenTomatoesSystem.addCategory(DraftCategory("anotherCat"))
        assertFailsWith<CategoryError>("Exist another category with same name") {
            rottenTomatoesSystem.editCategory("cat_1", DraftCategory("anotherCat"))
        }
    }

    @Test
    fun editCategoryWithCategoryIdNotFound() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addCategory(draftCategory)
        assertFailsWith<CategoryError>("Category not found") {
            rottenTomatoesSystem.editCategory("cat_2", DraftCategory("anotherCat"))
        }
    }

    @Test
    fun addUser() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.users.size, 0)
        rottenTomatoesSystem.addUser(draftUser)
        assertEquals(rottenTomatoesSystem.users.size, 1)
        val user = rottenTomatoesSystem.users[0]
        assertEquals(user.id, "u_1")
        assertEquals(user.name, "name")
        assertEquals(user.image, "image")
        assertEquals(user.email, "email@gmail.com")
        assertEquals(user.password, "password")
    }

    @Test
    fun addUserTwoTimes() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        rottenTomatoesSystem.addUser(draftUser)
        assertFailsWith<UserError>("Exist another user with same email") {
            rottenTomatoesSystem.addUser(draftUser)
        }
    }

    @Test
    fun addUserWithoutName() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftUser = DraftUser("", "image", "email@gmail.com", "password")
        assertFailsWith<UserError>("Name is empty") {
            rottenTomatoesSystem.addUser(customDraftUser)
        }
    }

    @Test
    fun addUserWithoutImage() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftUser = DraftUser("name", "", "email@gmail.com", "password")
        assertFailsWith<UserError>("Image is empty") {
            rottenTomatoesSystem.addUser(customDraftUser)
        }
    }

    @Test
    fun addUserWithoutEmail() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftUser = DraftUser("name", "image", "", "password")
        assertFailsWith<UserError>("Email is empty") {
            rottenTomatoesSystem.addUser(customDraftUser)
        }
    }

    @Test
    fun addUserWithNotValidEmail() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftUser = DraftUser("name", "image", "email@", "password")
        assertFailsWith<UserError>("Email is invalid") {
            rottenTomatoesSystem.addUser(customDraftUser)
        }
        val customDraftUser2 = DraftUser("name", "image", "email@asd.", "password")
        assertFailsWith<UserError>("Email is invalid") {
            rottenTomatoesSystem.addUser(customDraftUser2)
        }
        val customDraftUser3 = DraftUser("name", "image", "email", "password")
        assertFailsWith<UserError>("Email is invalid") {
            rottenTomatoesSystem.addUser(customDraftUser3)
        }
        val customDraftUser4 = DraftUser("name", "image", "@gmail.com", "password")
        assertFailsWith<UserError>("Email is invalid") {
            rottenTomatoesSystem.addUser(customDraftUser4)
        }

    }

    @Test
    fun addUserWithoutPassword() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        val customDraftUser = DraftUser("name", "image", "email@gmail.com", "")
        assertFailsWith<UserError>("Password is empty") {
            rottenTomatoesSystem.addUser(customDraftUser)
        }
    }

    @Test
    fun getUserById() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.users.size, 0)
        rottenTomatoesSystem.addUser(draftUser)
        assertEquals(rottenTomatoesSystem.users.size, 1)
        val user = rottenTomatoesSystem.getUserById("u_1")
        assertEquals(user.id, "u_1")
        assertEquals(user.name, "name")
        assertEquals(user.image, "image")
        assertEquals(user.email, "email@gmail.com")
        assertEquals(user.password, "password")
    }

    @Test
    fun getUserByIdWithoutUserId() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.users.size, 0)
        rottenTomatoesSystem.addUser(draftUser)
        assertEquals(rottenTomatoesSystem.users.size, 1)
        assertFailsWith<UserError>("UserId is empty") {
            rottenTomatoesSystem.getUserById("")
        }
    }

    @Test
    fun getUserByIdWithUserIdNotFound() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.users.size, 0)
        assertFailsWith<UserError>("User not found") {
            rottenTomatoesSystem.getUserById("u_1")
        }
    }

    @Test
    fun getMovieById() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        rottenTomatoesSystem.addMovie(draftMovie)
        assertEquals(rottenTomatoesSystem.movies.size, 1)
        val movie = rottenTomatoesSystem.getMovieById("mov_1")
        assertEquals(movie.id, "mov_1")
        assertEquals(movie.title, "mov1")
        assertEquals(movie.description, "mov1")
        assertEquals(movie.poster, "mov1")
    }

    @Test
    fun getMovieByIdWithoutMovieId() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        rottenTomatoesSystem.addMovie(draftMovie)
        assertEquals(rottenTomatoesSystem.movies.size, 1)
        assertFailsWith<MovieError>("MovieId is empty") {
            rottenTomatoesSystem.getMovieById("")
        }
    }

    @Test
    fun getMovieByIdWithMovieIdNotFound() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        assertEquals(rottenTomatoesSystem.movies.size, 0)
        assertFailsWith<MovieError>("Movie not found") {
            rottenTomatoesSystem.getMovieById("mov_1")
        }
    }

    @Test
    fun addReview() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        rottenTomatoesSystem.addUser(draftUser)

        assertEquals(rottenTomatoesSystem.reviews.size, 0)
        rottenTomatoesSystem.addReview(draftReview)
        assertEquals(rottenTomatoesSystem.reviews.size, 1)
        val review = rottenTomatoesSystem.reviews[0]
        assertEquals(review.id, "rev_1")
        assertEquals(review.user.id, "u_1")
        assertEquals(review.movie.id, "mov_1")
        assertEquals(review.text, "Text")
        assertEquals(review.stars, 3)
    }

    @Test
    fun addReviewTwoTimes() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        rottenTomatoesSystem.addUser(draftUser)

        assertEquals(rottenTomatoesSystem.reviews.size, 0)
        rottenTomatoesSystem.addReview(draftReview)
        assertEquals(rottenTomatoesSystem.reviews.size, 1)

        assertFailsWith<ReviewError>("User already have a review") {
            rottenTomatoesSystem.addReview(draftReview)
        }
    }

    @Test
    fun addReviewWithoutUserId() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        rottenTomatoesSystem.addUser(draftUser)

        val customDraftReview = DraftReview("", "mov_1", "Text", 3)

        assertFailsWith<UserError>("UserId is empty") {
            rottenTomatoesSystem.addReview(customDraftReview)
        }
    }

    @Test
    fun addReviewWithoutMovieId() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        rottenTomatoesSystem.addUser(draftUser)

        val customDraftReview = DraftReview("u_1", "", "Text", 3)

        assertFailsWith<MovieError>("MovieId is empty") {
            rottenTomatoesSystem.addReview(customDraftReview)
        }
    }

    @Test
    fun addReviewWithoutContent() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        rottenTomatoesSystem.addUser(draftUser)

        val customDraftReview = DraftReview("u_1", "mov_1", "", 3)

        assertFailsWith<ReviewError>("Content is empty") {
            rottenTomatoesSystem.addReview(customDraftReview)
        }
    }

    @Test
    fun addReviewWithStarsOutOfRange() {
        val rottenTomatoesSystem = RottenTomatoesSystem()
        rottenTomatoesSystem.addMovie(draftMovie)
        rottenTomatoesSystem.addUser(draftUser)

        val customDraftReview = DraftReview("u_1", "mov_1", "text", -1)

        assertFailsWith<ReviewError>("Stars out of range") {
            rottenTomatoesSystem.addReview(customDraftReview)
        }

        val customDraftReview2 = DraftReview("u_1", "mov_1", "text", 6)

        assertFailsWith<ReviewError>("Stars out of range") {
            rottenTomatoesSystem.addReview(customDraftReview2)
        }
    }

}