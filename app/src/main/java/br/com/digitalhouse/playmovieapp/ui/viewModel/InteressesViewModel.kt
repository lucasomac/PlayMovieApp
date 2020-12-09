package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Interesse

class InteressesViewModel(): ViewModel() {

    val interesses = MutableLiveData<ArrayList<Interesse>>()

    fun setInteresses() {

        interesses.value = arrayListOf<Interesse>(
            Interesse(1, "Sci-fi", true, R.drawable.ic_robot),
            Interesse(2, "Familia", false, R.drawable.ic_people),
            Interesse(3, "Terror", false, R.drawable.ic_ghost),
            Interesse(4, "Ação", false, R.drawable.ic_bomb),
            Interesse(5, "Romance", false, R.drawable.ic__heart),
            Interesse(6, "Documentário", false, R.drawable.ic_book),
            Interesse(7, "Suspense", false, R.drawable.ic_mask),
            Interesse(8, "Quadrinhos", true, R.drawable.ic_comics)
        )
    }

}