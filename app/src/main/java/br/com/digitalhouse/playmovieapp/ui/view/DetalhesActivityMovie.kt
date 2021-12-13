package br.com.digitalhouse.playmovieapp.ui.view

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.navArgs
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityDetalhesMovieBinding
import br.com.digitalhouse.playmovieapp.domain.movie.Movie
import br.com.digitalhouse.playmovieapp.getGenres
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.DetalhesActivityMovieViewModel
import com.bumptech.glide.Glide

class DetalhesActivityMovie : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesMovieBinding
    private lateinit var movie: Movie
    val viewModel by viewModels<DetalhesActivityMovieViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return DetalhesActivityMovieViewModel(repository) as T
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            ActivityDetalhesMovieBinding.inflate(layoutInflater).apply { setContentView(root) }
        initToolbar()
        val args: DetalhesActivityMovieArgs by navArgs()
        val movieId = args.idMovie
        Log.i("TAG", movieId.toString())
        movieId.let { viewModel.searchMovieById(it) }
        viewModel.movie.observe(this) {
            movie = it
            if (movie != null) {
                Glide.with(this).asBitmap().load(
                    "${BASE_URL_IMAGE}original${movie.backdrop_path}"
                ).placeholder(R.drawable.progress_animation).into(binding.imgCapaFilme)
                binding.txtSinopseFilme.text = movie.overview
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    binding.txtSinopseFilme.justificationMode = JUSTIFICATION_MODE_INTER_WORD
                }
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
            } else {
                Toast.makeText(this, "Não hã filmes disponiveis no momento!!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Sinopse do Filme")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }
}