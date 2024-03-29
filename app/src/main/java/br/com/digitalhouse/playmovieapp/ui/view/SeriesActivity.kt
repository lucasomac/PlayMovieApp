package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digitalhouse.playmovieapp.adapters.SeriesAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivitySeriesBinding
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.SeriesActivityViewModel

class SeriesActivity : AppCompatActivity(), SeriesAdapter.SerieListener {
    private lateinit var binding: ActivitySeriesBinding
    private lateinit var adapterSeries: SeriesAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var page = 1
    val viewModel by viewModels<SeriesActivityViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SeriesActivityViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeriesBinding.inflate(layoutInflater)
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
        val query = intent.getStringExtra("query")
        adapterSeries = SeriesAdapter(this)
        linearLayoutManager = LinearLayoutManager(this)
        binding.rvSeries.adapter = adapterSeries
        binding.rvSeries.layoutManager = linearLayoutManager
        binding.rvSeries.hasFixedSize()
        when {
            query.isNullOrBlank() -> {
                viewModel.discoverySeries(
                    page,
                    genero.toString(),
                    ano.toString(),
                    nota.toString()
                )
                viewModel.listResults.observe(this) {
                    adapterSeries.addSerie(it)
                }
            }
            else -> {
                viewModel.searchSeries(
                    page,
                    query.toString(),
                    genero.toString(),
                    ano.toString(),
                    nota.toString()
                )
                viewModel.listResults.observe(this) {
                    adapterSeries.addSerie(it)
                }
            }
        }

    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Resultado da busca")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun onClickSerie(position: Int) {
        val serie = adapterSeries.listaSeries[position]
        val intent = Intent(this, DetalhesActivitySerie::class.java)
        intent.putExtra("idSerie", serie.id.toString())
        startActivity(intent)
    }
}