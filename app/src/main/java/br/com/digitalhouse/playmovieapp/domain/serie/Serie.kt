package br.com.digitalhouse.playmovieapp.domain.serie

import br.com.digitalhouse.playmovieapp.domain.Genre

data class Serie(
    val backdrop_path: String,
    val created_by: ArrayList<CreatedBy>,
    val episode_run_time: ArrayList<Int>,
    val first_air_date: String,
    val genres: ArrayList<Genre>,
    val homepage: String,
    val id: Int,
    val in_production: Boolean,
    val languages: ArrayList<String>,
    val last_air_date: String,
    val last_episode_to_air: LastEpisodeToAir,
    val name: String,
    val networks: ArrayList<Network>,
    val next_episode_to_air: NextEpisodeToAir,
    val number_of_episodes: Int,
    val number_of_seasons: Int,
    val origin_country: ArrayList<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: ArrayList<ProductionCompany>,
    val production_countries: ArrayList<ProductionCountry>,
    val seasons: ArrayList<Season>,
    val spoken_languages: ArrayList<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val type: String,
    val vote_average: Double,
    val vote_count: Int
)

data class CreatedBy(
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val name: String,
    val profile_path: String
)

data class LastEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val season_number: Int,
    val still_path: String,
    val vote_average: Double,
    val vote_count: Int
)

data class Network(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

data class NextEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val season_number: Int,
    val still_path: String,
    val vote_average: Int,
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

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int
)

data class SpokenLanguage(
    val english_name: String,
    val iso_639_1: String,
    val name: String
)