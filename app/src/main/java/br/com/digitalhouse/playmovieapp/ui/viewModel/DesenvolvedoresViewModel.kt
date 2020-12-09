package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor

class DesenvolvedoresViewModel(): ViewModel() {

    val desenvolvedores = MutableLiveData<ArrayList<Desenvolvedor>>()

    fun setDesenvolvedores() {

        desenvolvedores.value = arrayListOf<Desenvolvedor>(
            Desenvolvedor(
                1,
                "Camila Pelegrina",
                "176 xícaras de café",
                23,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.perfil
            ),
            Desenvolvedor(
                2,
                "Danilo Augusto",
                "789 xícaras de café",
                23,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.perfil
            ),
            Desenvolvedor(
                3,
                "Lucas Oliveira",
                "462 xícaras de café",
                23,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.perfil
            ),
            Desenvolvedor(
                4,
                "Rodrigo Felipe",
                "178 xícaras de café",
                23,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.perfil
            ),
            Desenvolvedor(
                4,
                "Sarah Carneiro",
                "498 xícaras de café",
                23,
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                R.drawable.perfil
            )
        )
    }

}