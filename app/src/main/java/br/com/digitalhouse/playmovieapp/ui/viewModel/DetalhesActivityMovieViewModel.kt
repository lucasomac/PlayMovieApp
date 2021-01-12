package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.movie.Movie
import br.com.digitalhouse.playmovieapp.services.Repository
import kotlinx.coroutines.launch

class DetalhesActivityMovieViewModel(val repository: Repository) : ViewModel() {
    var movie = MutableLiveData<Movie>()
    fun searchMovieById(movie_id: Int) {
        viewModelScope.launch {
            repository.searchMovieDetail(movie_id, API_KEY, LANGUAGE).also {
                movie.postValue(it)
            }
        }
    }
}