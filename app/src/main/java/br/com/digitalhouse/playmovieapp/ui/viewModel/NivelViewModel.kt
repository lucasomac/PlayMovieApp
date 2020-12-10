package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.digitalhouse.playmovieapp.domain.Nivel

class NivelViewModel(): ViewModel() {

    val niveis = MutableLiveData<ArrayList<Nivel>>()

    fun setNiveis() {
        niveis.value = arrayListOf<Nivel>(
            Nivel(1, 12, 15),
            Nivel(2, 11, 15),
            Nivel(3, 8, 15),
            Nivel(4, 15, 15),
            Nivel(5, 1, 15),
            Nivel(6, 5, 15)
        )
    }

}