package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.serie.Result
import br.com.digitalhouse.playmovieapp.services.Repository
import kotlinx.coroutines.launch

class SeriesActivityViewModel(val repository: Repository) : ViewModel() {
    var listResults = MutableLiveData<ArrayList<Result>>()
    fun discoverySeries(
        page: Int,
        genre: String,
        first_air_date_year: String,
        vote_average: String
    ) {
        viewModelScope.launch {
            repository.searchSugestionTv(
                API_KEY,
                LANGUAGE,
                page,
                true,
                genre,
                first_air_date_year,
                vote_average
            ).also {
                listResults.postValue(it.results)
                Log.i("DATATA", listResults.toString())
            }
        }
    }

    fun searchSeries(
        page: Int,
        query: String,
        genre: String,
        first_air_date_year: String,
        vote_average: String
    ) {
        viewModelScope.launch {
            repository.searchTv(
                API_KEY,
                LANGUAGE,
                page,
                query,
                true,
                genre,
                first_air_date_year,
                vote_average
            ).also {
                listResults.postValue(it.results)
                Log.i("DATATA", listResults.toString())
            }
        }
    }
}