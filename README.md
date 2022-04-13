# UNQ » UIs » Dominio » Rotten tomatoes

[![](https://jitpack.io/v/unq-ui/2022s1-rottenTomatoes.svg)](https://jitpack.io/#unq-ui/2022s1-rottenTomatoes)


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
    <groupId>com.github.unq-ui</groupId>
    <artifactId>2022s1-rottenTomatoes</artifactId>
    <version>1.1.0</version>
</dependency>
```

### Interfaz de uso

```kotlin
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


# Requerimientos

* El sistema tiene que permitir agregar/editar películas, categorías y reviews.

* Una pelicula cuenta con:
  * Un id unico (con formato "mov_{numero}").
  * Un titulo (que no puede estar repetido en la aplicacion y no puede ser vacio).
  * Una descripcion (no puede ser vacia).
  * Un poster (que es una url) (no puede ser vacia).
  * Categorias relacionadas (son categorias tienen que estar dadas de alta previamente en el sistema).
  * Contenido relacionado (son peliculas dadas de alta previamente en el sistema).

* Una categoria cuenta con:
  * Un id unico (con formato "cat_{numero}").
  * Un nombre (que no puede estar repetido en la aplicacion y no puede ser vacio).

* Una Review cuenta con:
  * Un id unico (con formato "rev_{numero}").
  * Un usuario (que tiene que pertenecer al sistema)
  * Una Pelicula (que tiene que pertenecer al sistema)
  * Un texto (que no puede ser vacio)
  * Una cantidad de estrellas que van de 1 a 5.
  * Solo puede existir una review de un usuario por pelicula.

* Un usuario cuenta con:
  * Un id unico (con formato "u_{numero}").
  * Un nombre (que no puede ser vacio).
  * Una contraseña (que no puede ser vacia).
  * Un email (que no puede estar repetido en la aplicacion y no puede ser vacio).
  * Un listado de las reviews realizadas.

* TODA interaccion tiene que pasar por RottenTomatoesSystem (con la interfaz definida previamente).

* El RottenTomatoesSystem es el encargado de setear los ids de cada elemento que se agrega el sistema.
  * Para simplificar se utilizan objetos draft

```kotlin
class DraftMovie(
    var title: String,
    var description: String,
    var poster: String,
    var categories: MutableList<Category>,
    var relatedContent: MutableList<Movie>
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
```

* Se tiene que proveer datos de muestra (los datos se puede obtener de https://github.com/unq-ui/2022s1-rottenTomatoes/blob/master/src/main/kotlin/com/github/unqUi/model/bootstrap.kt)
