package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.Result
import br.com.digitalhouse.playmovieapp.services.Repository
import kotlinx.coroutines.launch

class FilmeDiaViewModel(val repository: Repository) : ViewModel() {
    var popularMovie = MutableLiveData<Result>()
    fun searchPopularMovie() {
        viewModelScope.launch {
            repository.searchPopularMovies(API_KEY, LANGUAGE, 1).results.get(1).also {
                popularMovie.postValue(it)
            }
        }
    }
}