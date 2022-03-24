# UNQ » UIs » Dominio » UNQFlix

[![JitPack](https://jitpack.io/v/unq-ui/unqflix.svg)](https://jitpack.io/#unq-ui/unqflix)

Construcción de Interfaces de Usuario, Universidad Nacional de Quilmes.

## TP 2022s1

### Dependencia

Agregar el repositorio:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Agregar la dependencia:

```xml
<dependency>
    <groupId>com.github.unqUi</groupId>
    <artifactId>RottenTomatos</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Interfaz de uso

```kt
class RottenTomatoesSystem {

  val movies: MutableList<Movie>
  val categories: MutableList<Category>
  val users: MutableList<User>

  @throw MovieError (
    * El titulo, descripcion o poster no puede ser vacios.
    * El titulo se encuentra repetido.
  )
  fun addMovie(movie: DraftMovie): Movie

  @throw CategoryError (
    * El nombre no puede ser vacio.
    * Existe otra categoria con el mismo nombre.
  )
  fun addCategory(category: DraftCategory): Category

  @throw UserError (
    * El nombre, image, email o password no pueden ser vacios.
    * Si el email no es valido.
    * El email se encuentra repetido.
  )
  fun addUser(user: DraftUser): User

  @throw ReviewError (
    * El contenido esta vacio.
    * Si las estrellas son menores a 0 o mayores a 5.
  )
  @throw UserError (
    * Si el userId es vacio.
    * Si no existe el userId.
  )
  @throw MovieError (
    * Si el movieId es vacio.
    * Si no existe el movieId.
  )
  fun addReview(review: DraftReview): Review 

  @throw UserError (
    * Si el userId es vacio.
    * Si no existe el userId.
  )
  fun getUserById(userId: String): User 

    @throw MovieError (
    * Si el movieId es vacio.
    * Si no existe el movieId.
  )
  fun getMovieById(movieId: String): Movie

}
```
