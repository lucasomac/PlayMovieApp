package br.com.digitalhouse.playmovieapp

import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.domain.Nivel

val API_KEY = "e9e3353941b599fd1f05fc8257a975d8"

//val API_KEY = System.getenv("apikey")
val API_MARVEL_URL = "https://api.themoviedb.org/3/"
val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/"
val LANGUAGE = "pt-BR"
val INCLUDE_ADULT = true

fun getNiveis(): ArrayList<Nivel> {
    return arrayListOf<Nivel>(
        Nivel(1, 12, 15),
        Nivel(2, 11, 15),
        Nivel(3, 8, 15),
        Nivel(4, 15, 15),
        Nivel(5, 1, 15),
        Nivel(6, 5, 15)
    )
}

fun getGenres() {
    return arrayListOf<Genre>(
        Genre(28, "Ação"),
        Genre(12, "Ação"),
        Genre(10759, "Action & Adventure"),
        Genre(16, "Animação"),
        Genre(35, "Comédia"),
        Genre(80, "Crime"),
        Genre(99, "Documentário"),
        Genre(18, "Drama"),
        Genre(10751, "Família"),
        Genre(14, "Fantasia"),
        Genre(36, "História"),
        Genre(27, "Terror"),
        Genre(10402, "Música"),
        Genre(9648, "Mistério"),
        Genre(10749, "Romance"),
        Genre(878, "Ficção científica"),
        Genre(10770, "Cinema TV"),
        Genre(53, "Thriller"),
        Genre(10752, "Guerra"),
        Genre(37, "Faroeste"),
        Genre(10762, "Kids"),
        Genre(10763, "News"),
        Genre(10764, "Reality"),
        Genre(10765, "Sci-Fi & Fantasy"),
        Genre(10766, "Soap"),
        Genre(10767, "Talk"),
        Genre(10768, "War & Politics")
    ).sortBy { it.id }
}