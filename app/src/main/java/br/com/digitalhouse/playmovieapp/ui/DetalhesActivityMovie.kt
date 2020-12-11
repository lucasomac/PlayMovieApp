package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.databinding.ActivityDetalhesMovieBinding
import br.com.digitalhouse.playmovieapp.domain.Movie
import br.com.digitalhouse.playmovieapp.getGenres
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.DetalhesActivityMovieViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.app_toolbar.*

class DetalhesActivityMovie : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesMovieBinding
    private lateinit var movie: Movie
    val viewModel by viewModels<DetalhesActivityMovieViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetalhesActivityMovieViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        val args: DetalhesActivityMovieArgs by navArgs()
        val movie_id = args.idMovie
        Log.i("TAG", movie_id.toString())
        movie_id.let { viewModel.searchMovieById(it) }
        viewModel.movie.observe(this) {
            movie = it
            Glide.with(this).asBitmap().load(
                "${BASE_URL_IMAGE}original${movie.backdrop_path}"
            ).into(binding.imgCapaFilme)
            binding.txtSinopseFilme.text = movie.overview
            binding.txtNomeFilme.text = movie.title
            binding.txtNotaFilme.text = movie.vote_average.toString()
            val generos = arrayListOf<String>()
            for (genre in movie.genres) {
                generos.add(getGenres().filter { it.id == genre.id }[0].name)
            }
            "${movie.release_date.substring(0, 4)} - ${
                "$generos".trim(
                    '[',
                    ']'
                )
            } - ${movie.runtime}min".also { binding.txtDetalhesFilme.text = it }
        }
    }

    private fun initToolbar() {
        val toolbar = material_toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }
}

//EX.00905.07/2020-P
//89029208520
