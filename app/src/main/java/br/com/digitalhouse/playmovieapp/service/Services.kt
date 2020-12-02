package br.com.digitalhouse.playmovieapp.service

import br.com.digitalhouse.playmovieapp.domain.Entities
import br.com.digitalhouse.playmovieapp.domain.Movie
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

val apiMarvelUrl = "https://gateway.marvel.com/v1/public/"

interface Services {
    @GET("/search/movie")
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

    @GET("/search/{movie_id}")
    suspend fun searchMovieDetail(
        @Query("api_key")
        apikey: String,
        @Query("language")
        @Path("movie_id")
        movie_id: Int
    ): Movie

}

val retrofit = Retrofit.Builder()
    .baseUrl(apiMarvelUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val repository: Services = retrofit.create(Services::class.java)