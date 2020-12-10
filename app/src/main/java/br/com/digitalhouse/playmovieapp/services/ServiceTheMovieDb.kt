package br.com.digitalhouse.playmovieapp.services

import br.com.digitalhouse.playmovieapp.API_TMDB_URL
import br.com.digitalhouse.playmovieapp.domain.Entities
import br.com.digitalhouse.playmovieapp.domain.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Repository {
    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("query")
        query: String,
        @Query("include_adult")
        include_adult: Boolean,
        @Query("page")
        page: Int,
    ): Entities

    @GET("movie/popular")
    suspend fun searchPopularMovies(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
    ): Entities

    @GET("movie/{movie_id}")
    suspend fun searchMovieDetail(
        @Path("movie_id")
        movie_id: Int,
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String
    ): Movie

    @GET("genre/movie/list")
    suspend fun searchGenre(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
    ): Movie

    @GET("discover/movie")
    suspend fun searchSugestionMovie(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
        @Query("with_genres")
        with_genres: String,
        @Query("year")
        year: String,
        @Query("vote_average.gte")
        vote_average: String,
    ): Entities

    @GET("discover/tv")
    suspend fun searchSugestionTv(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("with_genres")
        with_genres: Int,
        @Query("year")
        year: Int,
        @Query("vote_average.gte")
        vote_average: Double,
    ): Movie

}

val retrofit =
    Retrofit.Builder().baseUrl(API_TMDB_URL).addConverterFactory(GsonConverterFactory.create())
        .build()
val repository: Repository = retrofit.create(Repository::class.java)