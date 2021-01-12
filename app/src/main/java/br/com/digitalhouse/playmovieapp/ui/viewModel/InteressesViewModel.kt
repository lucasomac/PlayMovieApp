package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Interesse
import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.getGenres
import br.com.digitalhouse.playmovieapp.services.RepositorySqlite
import kotlinx.coroutines.launch

class InteressesViewModel(val repository: RepositorySqlite) : ViewModel() {

    val interesses = MutableLiveData<ArrayList<Interesse>>()

    //Adicionar um novo interesse
    fun addNewGenre(genre: Genre) {
        viewModelScope.launch {
            repository.insertGenreTask(genre)
        }
    }

    //Deletar um interesse
    fun delGenre(genre: Genre) {
        viewModelScope.launch {
            repository.deleteGenreTask(genre)
        }
    }

    fun setInteresses() {

        interesses.value = arrayListOf<Interesse>(
            Interesse(getGenres().filter { it.name == "Ação" }[0], false, R.drawable.ic_bomb),
            Interesse(getGenres().filter { it.name == "Aventura" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Action & Adventure" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Animação" }[0], true, R.drawable.ic_comics),
            Interesse(getGenres().filter { it.name == "Comédia" }[0], true, 0),
            Interesse(getGenres().filter { it.name == "Crime" }[0], true, 0),
            Interesse(getGenres().filter { it.name == "Documentário" }[0], false, R.drawable.ic_book),
            Interesse(getGenres().filter { it.name == "Drama" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Família" }[0], false, R.drawable.ic_people),
            Interesse(getGenres().filter { it.name == "Fantasia" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "História" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Terror" }[0], false, R.drawable.ic_ghost),
            Interesse(getGenres().filter { it.name == "Música" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Mistério" }[0], false, R.drawable.ic_mask),
            Interesse(getGenres().filter { it.name == "Romance" }[0], false, R.drawable.ic__heart),
            Interesse(getGenres().filter { it.name == "Ficção científica" }[0], true, R.drawable.ic_robot),
            Interesse(getGenres().filter { it.name == "Cinema TV" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Thriller" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Guerra" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Faroeste" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Kids" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "News" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Reality" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Sci-Fi & Fantasy" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Soap" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "Talk" }[0], false, 0),
            Interesse(getGenres().filter { it.name == "War & Politics" }[0], false, 0)
        )
    }

}