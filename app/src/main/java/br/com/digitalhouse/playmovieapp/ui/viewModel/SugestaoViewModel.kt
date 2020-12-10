package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SugestaoViewModel: ViewModel() {

    var generoSelecionado = MutableLiveData<String>()
    var categoriaSelecionada = MutableLiveData<String>()
    var notaSelecionada = MutableLiveData<String>()
    var anoSelecionado = MutableLiveData<String>()

    fun setGeneroSelecionado(str: String) {
        generoSelecionado.value = str
    }

    fun setCategoriaSelecionada(str: String) {
        categoriaSelecionada.value = str
    }

    fun setNotaSelecionada(str: String) {
        notaSelecionada.value = str
    }

    fun setAnoSelecionado(str: String) {
        anoSelecionado.value = str
    }
}