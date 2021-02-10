package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.API_KEY
import br.com.digitalhouse.playmovieapp.LANGUAGE
import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.domain.movie.Result
import br.com.digitalhouse.playmovieapp.services.Repository
import br.com.digitalhouse.playmovieapp.services.RepositoryRoom
import br.com.digitalhouse.playmovieapp.services.collectionUsers
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeActivityViewModel(
    val repositoryRoom: RepositoryRoom? = null,
    val repository: Repository,
) : ViewModel() {
    val genres = MutableLiveData<List<Genre>>()
    var movie = MutableLiveData<Result>()
    var pontuacao = MutableLiveData<Int>()

    fun selectGenres() {
        viewModelScope.launch {
            genres.value = repositoryRoom?.selectAllGenreTask()
        }
    }

    fun getPontuacao(email: String) {
        viewModelScope.launch {
            collectionUsers.document(email).collection("answered").get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var pontos = 0
                        for (document in task.result!!) {
                            pontos += document.data["score"].toString().toInt()
                        }
                        pontuacao.value = pontos
                    } else {
                        Log.i("SubNivelViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }

    fun discoveryMovies(page: Int, genre: String, year: String, vote_average: String) {
        viewModelScope.launch {
            repository.searchSugestionMovie(
                API_KEY,
                LANGUAGE,
                page,
                true,
                genre.trim(' ', '[', ']'),
                year,
                vote_average
            ).also {
                movie.postValue(
                    it.results[Random.nextInt(0, it.results.size)]
                )
            }
        }
    }
}