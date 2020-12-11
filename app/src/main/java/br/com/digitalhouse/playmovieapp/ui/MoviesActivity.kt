package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digitalhouse.playmovieapp.adapters.MovieAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivityMoviesBinding
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.MoviesActivityViewModel
import kotlinx.android.synthetic.main.app_toolbar.*

class MoviesActivity : AppCompatActivity(), MovieAdapter.MovieListener {
    private lateinit var binding: ActivityMoviesBinding
    private lateinit var adapterMovie: MovieAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    var page = 1
    val viewModel by viewModels<MoviesActivityViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MoviesActivityViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        val genero =
            intent.getStringExtra("generoSelecionado")
        val categoria =
            intent.getStringExtra("categoriaSelecionada")
        val nota = if (intent.getStringExtra("notaSelecionada") != "Qualquer nota")
            intent.getStringExtra("notaSelecionada")?.let { it.get(it.length - 1) } else "0"
        val ano = if (intent.getStringExtra("anoSelecionado") != "Qualquer ano")
            intent.getStringExtra("anoSelecionado")?.let { it.substring(it.length - 4) } else "0"
        adapterMovie = MovieAdapter(this)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapterMovie
        binding.rvMovies.layoutManager = linearLayoutManager
        binding.rvMovies.hasFixedSize()
        when (categoria) {
            "Filme" -> {
                viewModel.searchMoviesFilter(
                    page,
                    genero.toString(),
                    ano.toString(),
                    nota.toString()
                )
                viewModel.listResults.observe(this) {
                    adapterMovie.addMovie(it)
                }
            }
            "Série" -> {
                viewModel.searchMoviesFilter(
                    page,
                    genero.toString(),
                    ano.toString(),
                    nota.toString()
                )
                viewModel.listResults.observe(this) {
                    adapterMovie.addMovie(it)
                }
            }
        }

    }

    private fun initToolbar() {
        val toolbar = material_toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Busca")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onClickMovie(item: Int) {
        TODO("Not yet implemented")
    }

    fun primeiroDigitoEhUmNumero(entrada: String): Boolean {
        val primeiroDigito = entrada[0].toString() + ""
        return try {
            primeiroDigito.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
}