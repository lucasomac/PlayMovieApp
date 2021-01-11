package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.getGenres
import br.com.digitalhouse.playmovieapp.ui.viewModel.PesquisaViewModel
import com.skydoves.powerspinner.PowerSpinnerView
import kotlinx.android.synthetic.main.activity_pesquisa.*
import kotlinx.android.synthetic.main.app_toolbar.*

class PesquisaActivity : AppCompatActivity() {

    val viewModel: PesquisaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pesquisa)
        initToolbar()

        InitSpinnerAnos()
        InitSpinnerGeneros()
        InitSpinnerCategorias()
        InitSpinnerNotas()

        viewModel.categoriaSelecionada.observe(this) {
            when (viewModel.categoriaSelecionada.value.toString()) {
                "Filme" -> {
                    fab_sugestao.setOnClickListener(openMovieDetail())
                }
                "Série" -> {
                    fab_sugestao.setOnClickListener(openSerieDetail())
                }
            }
        }
    }

    fun InitSpinnerAnos() {
        val anos = resources.getStringArray(R.array.anos)
        val view = findViewById<PowerSpinnerView>(R.id.spinnerAno)

        view.setItems(itemList = anos.toList())
        view.setOnSpinnerItemSelectedListener<String> { _, item ->
            viewModel.setAnoSelecionado(
                item
            )
        }
    }

    fun InitSpinnerGeneros() {
        val generos = resources.getStringArray(R.array.generos)
        val view = findViewById<PowerSpinnerView>(R.id.spinnerGeneros)

        view.setItems(itemList = generos.toList())
        view.setOnSpinnerItemSelectedListener<String> { _, item ->
            viewModel.setGeneroSelecionado(
                item
            )
        }
    }

    fun InitSpinnerCategorias() {
        val categorias = resources.getStringArray(R.array.categorias)
        val view = findViewById<PowerSpinnerView>(R.id.spinnerCategorias)

        view.setItems(itemList = categorias.toList())
        view.setOnSpinnerItemSelectedListener<String> { _, item ->
            viewModel.setCategoriaSelecionada(
                item
            )
        }
    }

    fun InitSpinnerNotas() {
        val notas = resources.getStringArray(R.array.notas)
        val view = findViewById<PowerSpinnerView>(R.id.spinnerNota)

        view.setItems(itemList = notas.toList())
        view.setOnSpinnerItemSelectedListener<String> { _, item ->
            viewModel.setNotaSelecionada(
                item
            )
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
                "query", text_busca.text.toString()
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
                "query", text_busca.text.toString()
            )
        }
        startActivity(intent)
    }

    private fun retornaId(genre: String): Int {
        return getGenres().sortedBy { it.name }.filter { it.name == genre }[0].id
    }

    private fun initToolbar() {
        val toolbar = material_toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Pesquisa de conteúdo")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }
}