package br.com.digitalhouse.playmovieapp.services

import br.com.digitalhouse.playmovieapp.API_TMDB_URL
import br.com.digitalhouse.playmovieapp.domain.Movie
import br.com.digitalhouse.playmovieapp.domain.movie.ListMovie
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
        @Query("query")
        query: String,
        @Query("include_adult")
        include_adult: Boolean,
        @Query("page")
        page: Int,
    ): ListMovie

    @GET("movie/popular")
    suspend fun searchPopularMovies(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
    ): ListMovie

    @GET("tv/{tv_id}")
    suspend fun searchSerieDetail(
        @Path("tv_id")
        tv_id: String,
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String
    ): Serie

    @GET("movie/{movie_id}")
    suspend fun searchMovieDetail(
        @Path("movie_id")
        movie_id: Int,
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String
    ): Movie

//    @GET("genre/movie/list")
//    suspend fun searchGenre(
//        @Query("api_key")
//        apikey: String,
//        @Query("language")
//        language: String,
//    ): Movie

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
    ): ListMovie

    @GET("discover/tv")
    suspend fun searchSugestionTv(
        @Query("api_key")
        apikey: String,
        @Query("language")
        language: String,
        @Query("page")
        page: Int,
        @Query("with_genres")
        with_genres: String,
        @Query("first_air_date_year")
        first_air_date_year: String,
        @Query("vote_average.gte")
        vote_average: String,
    ): ListSerie

}

val retrofit =
    Retrofit.Builder().baseUrl(API_TMDB_URL).addConverterFactory(GsonConverterFactory.create())
        .build()
val repository: Repository = retrofit.create(Repository::class.java)