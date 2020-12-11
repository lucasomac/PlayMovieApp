package br.com.digitalhouse.playmovieapp

import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.domain.Nivel

val API_KEY = "e9e3353941b599fd1f05fc8257a975d8"

//val API_KEY = System.getenv("apikey")
val API_TMDB_URL = "https://api.themoviedb.org/3/"
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

fun getGenres(): ArrayList<Genre> {
    return arrayListOf<Genre>(
        Genre(28, "Ação"),//m
        Genre(12, "Aventura"),//m
        Genre(10759, "Action & Adventure"), //s
        Genre(16, "Animação"), //sm
        Genre(35, "Comédia"),//sm
        Genre(80, "Crime"),//sm
        Genre(99, "Documentário"),//sm
        Genre(18, "Drama"),//sm
        Genre(10751, "Família"),//sm
        Genre(14, "Fantasia"),//m
        Genre(36, "História"),//m
        Genre(27, "Terror"),//m
        Genre(10402, "Música"),//m
        Genre(9648, "Mistério"),//sm
        Genre(10749, "Romance"),//m
        Genre(878, "Ficção científica"),//m
        Genre(10770, "Cinema TV"),//m
        Genre(53, "Thriller"),//m
        Genre(10752, "Guerra"),//m
        Genre(37, "Faroeste"),//sm
        Genre(10762, "Kids"),//s
        Genre(10763, "News"),//s
        Genre(10764, "Reality"),//s
        Genre(10765, "Sci-Fi & Fantasy"),//s
        Genre(10766, "Soap"),//s
        Genre(10767, "Talk"),//s
        Genre(10768, "War & Politics")//s
    )
    @Suppress("UNREACHABLE_CODE")
    fun retornaId(genre: String): Int {
        return getGenres().sortedBy { it.name }.filter { it.name == genre }[0].id
    }

}