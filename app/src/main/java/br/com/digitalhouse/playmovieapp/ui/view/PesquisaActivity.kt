package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityPesquisaBinding
import br.com.digitalhouse.playmovieapp.getGenres
import br.com.digitalhouse.playmovieapp.ui.viewModel.PesquisaViewModel

class PesquisaActivity : AppCompatActivity() {

    val viewModel: PesquisaViewModel by viewModels()
    private lateinit var binding: ActivityPesquisaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesquisaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()

        InitSpinnerAnos()
        InitSpinnerGeneros()
        InitSpinnerCategorias()
        InitSpinnerNotas()
        viewModel.categoriaSelecionada.observe(this) {
            when (viewModel.categoriaSelecionada.value.toString()) {
                "Filme" -> {
                    binding.btnSugestao.setOnClickListener(openMovieDetail())
                }
                "Série" -> {
                    binding.btnSugestao.setOnClickListener(openSerieDetail())
                }
            }
        }
    }

    fun InitSpinnerAnos() {

        val view = findViewById<Spinner>(R.id.spinnerAno)
        val anos = resources.getStringArray(R.array.anos)
        view.adapter =
            ArrayAdapter(view.context, R.layout.spinner_item, anos)

        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setAnoSelecionado(anos[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun InitSpinnerGeneros() {

        val view = findViewById<Spinner>(R.id.spinnerGeneros)
        val generos = resources.getStringArray(R.array.generos)
        view.adapter =
            ArrayAdapter(view.context, R.layout.spinner_item, generos)

        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setGeneroSelecionado(generos[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun InitSpinnerCategorias() {

        val view = findViewById<Spinner>(R.id.spinnerCategorias)
        val categorias = resources.getStringArray(R.array.categorias)
        view.adapter =
            ArrayAdapter(view.context, R.layout.spinner_item, categorias)

        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setCategoriaSelecionada(categorias[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun InitSpinnerNotas() {

        val view = findViewById<Spinner>(R.id.spinnerNota)
        val notas = resources.getStringArray(R.array.notas)
        view.adapter =
            ArrayAdapter(view.context, R.layout.spinner_item, notas)

        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.setNotaSelecionada(notas[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    fun openMovieDetail(): View.OnClickListener = View.OnClickListener {

        val intent = Intent(
            this,
            MoviesActivity::class.java
        ).apply {
            putExtra(
                "generoSelecionado",
                viewModel.generoSelecionado.value?.let { it -> retornaId(it).toString() })
            putExtra(
                "categoriaSelecionada", viewModel.categoriaSelecionada.value
            )
            putExtra(
                "notaSelecionada", viewModel.notaSelecionada.value
            )
            putExtra(
                "anoSelecionado", viewModel.anoSelecionado.value
            )
            putExtra(
                "query", binding.etBusca.text.toString()
            )
        }
        startActivity(intent)
    }

    private fun openSerieDetail(): View.OnClickListener = View.OnClickListener {
        val intent = Intent(
            this,
            SeriesActivity::class.java
        ).apply {
            putExtra(
                "generoSelecionado",
                viewModel.generoSelecionado.value?.let { it -> retornaId(it).toString() })
            putExtra(
                "categoriaSelecionada", viewModel.categoriaSelecionada.value
            )
            putExtra(
                "notaSelecionada", viewModel.notaSelecionada.value
            )
            putExtra(
                "anoSelecionado", viewModel.anoSelecionado.value
            )
            putExtra(
                "query", binding.etBusca.text.toString()
            )
        }
        startActivity(intent)
    }

    private fun retornaId(genre: String): Int {
        return getGenres().sortedBy { it.name }.filter { it.name == genre }[0].id
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Pesquisa de conteúdo")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }
}