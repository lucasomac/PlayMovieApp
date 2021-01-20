package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.domain.movie.Result
import br.com.digitalhouse.playmovieapp.services.Repository
import br.com.digitalhouse.playmovieapp.services.RepositoryRoom
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeActivityViewModel(
    val repositoryRoom: RepositoryRoom? = null,
    val repository: Repository
) : ViewModel() {
    val genres = MutableLiveData<List<Genre>>()
    var movie = MutableLiveData<Result>()

    fun selectGenres() {
        viewModelScope.launch {
            genres.value = repositoryRoom?.selectAllGenreTask()
        }
    }


    fun discoveryMovies(page: Int, genre: String, year: String, vote_average: String) {
        viewModelScope.launch {
            repository.searchSugestionMovie(
                API_KEY,
                LANGUAGE,
                page,
                true,
                genre,
                year,
                vote_average
            ).also {
                movie.postValue(null)
            }
        }
    }
}