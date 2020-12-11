package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.serie.Serie
import br.com.digitalhouse.playmovieapp.services.Repository
import kotlinx.coroutines.launch

class DetalhesActivitySerieViewModel(val repository: Repository) : ViewModel() {
    var serie = MutableLiveData<Serie>()
    fun searchSerieById(serie_id: String) {
        viewModelScope.launch {
            repository.searchSerieDetail(serie_id, API_KEY, LANGUAGE).also {
                serie.postValue(it)
            }
        }
    }
}