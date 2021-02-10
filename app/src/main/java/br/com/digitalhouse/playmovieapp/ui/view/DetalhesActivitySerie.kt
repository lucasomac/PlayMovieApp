package br.com.digitalhouse.playmovieapp.ui.view

import android.graphics.text.LineBreaker
import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.databinding.ActivityDetalhesSerieBinding
import br.com.digitalhouse.playmovieapp.domain.serie.Serie
import br.com.digitalhouse.playmovieapp.getGenres
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.DetalhesActivitySerieViewModel
import com.bumptech.glide.Glide

class DetalhesActivitySerie : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesSerieBinding
    private lateinit var serie: Serie
    val viewModel by viewModels<DetalhesActivitySerieViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return DetalhesActivitySerieViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesSerieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
//        val args: DetalhesActivitySerieArgs by navArgs()
        val serie_id = intent.getStringExtra("idSerie").toString()
        serie_id.let { viewModel.searchSerieById(it) }
        viewModel.serie.observe(this) {
            serie = it
            Glide.with(this).asBitmap().load(
                "${BASE_URL_IMAGE}original${serie.backdrop_path}"
            ).into(binding.imgCapaSerie)
            binding.txtSinopseSerie.text = serie.overview
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                binding.txtSinopseSerie.justificationMode = JUSTIFICATION_MODE_INTER_WORD
            }
            binding.txtNomeSerie.text = serie.name
            binding.txtNotaSerie.text = serie.vote_average.toString()
            val generos = arrayListOf<String>()
            for (genre in serie.genres) {
                generos.add(getGenres().filter { it.id == genre.id }[0].name)
            }
            "${serie.first_air_date.substring(0, 4)} - ${
                "$generos".trim(
                    '[',
                    ']'
                )
            } - ${serie.episode_run_time[0]}min por epiósdio".also {
                binding.txtDetalhesSerie.text = it
            }
        }
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Sinopse da Série")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }
}