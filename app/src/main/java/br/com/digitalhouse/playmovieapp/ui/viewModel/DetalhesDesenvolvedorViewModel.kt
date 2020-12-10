package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor

class DetalhesDesenvolvedorViewModel: ViewModel() {

    val desenvolvedor = MutableLiveData<Desenvolvedor>()

    fun setDesenvolvedor(dev: Desenvolvedor) {

        desenvolvedor.value = dev
    }
}