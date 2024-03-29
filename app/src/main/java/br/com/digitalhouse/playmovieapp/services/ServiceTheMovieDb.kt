package br.com.digitalhouse.playmovieapp.services

import br.com.digitalhouse.playmovieapp.API_TMDB_URL
import br.com.digitalhouse.playmovieapp.domain.movie.Movie
import br.com.digitalhouse.playmovieapp.domain.movie.ListMovie
import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.domain.serie.ListSerie
import br.com.digitalhouse.playmovieapp.domain.serie.Serie
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
        @Query("page")
        page: Int,
        @Query("query")
        query: String,
        @Query("include_adult")
        include_adult: Boolean,
        @Query("with_genres")
        with_genres: String,
        @Query("year")
        year: String,
        @Query("vote_average.gte")
        vote_average: String,
    ): ListMovie

    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
    ): ListMovie

    @GET("discover/movie")
    suspend fun searchSugestionMovie(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
        @Query("include_adult")
        include_adult: Boolean,
        @Query("with_genres")
        with_genres: String,
        @Query("year")
        year: String,
        @Query("vote_average.gte")
        vote_average: String,
    ): ListMovie

    @GET("movie/{movie_id}")
    suspend fun searchMovieDetail(
        @Path("movie_id")
        movie_id: Int,
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
    ): Movie

    @GET("search/tv")
    suspend fun searchTv(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
        @Query("query")
        query: String,
        @Query("include_adult")
        include_adult: Boolean,
        @Query("with_genres")
        with_genres: String,
        @Query("first_air_date_year")
        first_air_date_year: String,
        @Query("vote_average.gte")
        vote_average: String,
    ): ListSerie

    @GET("discover/tv")
    suspend fun searchSugestionTv(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
        @Query("include_adult")
        include_adult: Boolean,
        @Query("with_genres")
        with_genres: String,
        @Query("first_air_date_year")
        first_air_date_year: String,
        @Query("vote_average.gte")
        vote_average: String,
    ): ListSerie

    @GET("tv/{tv_id}")
    suspend fun searchSerieDetail(
        @Path("tv_id")
        tv_id: String,
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
    ): Serie


    @GET("movie/popular")
    suspend fun searchPopularMovies(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
    ): ListMovie

    @GET("genre/movie/list")
    suspend fun searchGenre(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
    ): Genre
}

val retrofit: Retrofit =
    Retrofit.Builder().baseUrl(API_TMDB_URL).addConverterFactory(GsonConverterFactory.create())
        .build()
val repository: Repository = retrofit.create(Repository::class.java)