package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.Result
import br.com.digitalhouse.playmovieapp.services.Repository
import kotlinx.coroutines.launch

class MoviesActivityViewModel(val repository: Repository) : ViewModel() {
    var listResults = MutableLiveData<ArrayList<Result>>()
    fun searchMoviesFilter(page: Int, genre: Int, year: Int, vote_average: Double) {
        viewModelScope.launch {
            repository.searchSugestionMovie(
                API_KEY,
                LANGUAGE,
                page,
                genre,
                year,
                vote_average
            ).also {
                listResults.postValue(it.results)
                Log.i("DATATA", listResults.toString())
            }
        }
    }
}