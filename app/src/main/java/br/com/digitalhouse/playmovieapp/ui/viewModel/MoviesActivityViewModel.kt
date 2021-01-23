package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.movie.Result
import br.com.digitalhouse.playmovieapp.services.Repository
import kotlinx.coroutines.launch

class MoviesActivityViewModel(val repository: Repository) : ViewModel() {
    var listResults = MutableLiveData<ArrayList<Result>>()
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
                listResults.postValue(it.results)
                Log.i("DATATA", listResults.toString())
            }
        }
    }

    fun searchMovies(page: Int, query: String, genre: String, year: String, vote_average: String) {
        viewModelScope.launch {
            repository.searchMovie(
                API_KEY,
                LANGUAGE,
                page,
                query,
                true,
                genre,
                year,
                vote_average
            ).also {
//                with(listResults) {
//                    Log.i("DATATA23", it.results.toString())
//                    postValue(it.results
//                        .filter { !it.poster_path.isEmpty() }
//                        .filter { !it.original_title.isEmpty() }
//                        .filter { !it.overview.isEmpty() }
//                        .forEach {
//                            it.release_date = it.release_date.substring(0, 4)
//                        } as ArrayList<Result>?
//                    )
//                    Log.i("DATATA22", it.results.toString())
//                }
                listResults.postValue(it.results)
                Log.i("DATATA22", listResults.toString())
            }
        }
    }
}