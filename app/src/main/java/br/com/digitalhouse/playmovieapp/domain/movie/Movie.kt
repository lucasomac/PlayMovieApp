package br.com.digitalhouse.playmovieapp.domain.movie

import br.com.digitalhouse.playmovieapp.domain.Genre

data class Movie(
    val adult: Boolean,
    val backdrop_path: Any,
    val belongs_to_collection: Any,
    val budget: Int,
    val genres: ArrayList<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: ArrayList<ProductionCompany>,
    val production_countries: ArrayList<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: ArrayList<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)