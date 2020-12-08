package br.com.digitalhouse.playmovieapp

import br.com.digitalhouse.playmovieapp.domain.Nivel

val API_KEY = System.getenv("apikey")
val API_MARVEL_URL = "https://gateway.marvel.com/v1/public/"
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