package br.com.digitalhouse.playmovieapp.domain

data class Entities(
    val page: Int,
    val results: ArrayList<Result>,
    val total_pages: Int,
    val total_results: Int
)

data class Result(
    val adult: Boolean,
    val backdrop_path: Any,
    val genre_ids: ArrayList<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)