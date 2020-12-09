package br.com.digitalhouse.playmovieapp.services

import br.com.digitalhouse.playmovieapp.API_MARVEL_URL
import br.com.digitalhouse.playmovieapp.domain.Entities
import br.com.digitalhouse.playmovieapp.domain.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Repository {
    @GET("search/movie")
    suspend fun searchMovies(
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

    @GET("search/{movie_id}")
    suspend fun searchMovieDetail(
        @Query("api_key")
        apikey: String,
        @Query("language")
        @Path("movie_id")
        movie_id: Int
    ): Movie

}

val retrofit =
    Retrofit.Builder().baseUrl(API_MARVEL_URL).addConverterFactory(GsonConverterFactory.create())
        .build()
val repository: Repository = retrofit.create(Repository::class.java)