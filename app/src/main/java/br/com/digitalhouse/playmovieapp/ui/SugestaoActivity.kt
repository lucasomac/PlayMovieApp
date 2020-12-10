package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.ui.viewModel.NivelViewModel
import br.com.digitalhouse.playmovieapp.ui.viewModel.SugestaoViewModel
import kotlinx.android.synthetic.main.activity_sugestao.*

class SugestaoActivity : AppCompatActivity() {

    val viewModel: SugestaoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugestao)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        InitSpinnerAnos()
        InitSpinnerGeneros()
        InitSpinnerCategorias()
        InitSpinnerNotas()

        btnSugestao.setOnClickListener(openMovieDetail())
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

    fun openMovieDetail(): View.OnClickListener? = View.OnClickListener {
        val bundle = Bundle()
        bundle.putString("generoSelecionado", viewModel.generoSelecionado.value)
        bundle.putString("categoriaSelecionada", viewModel.categoriaSelecionada.value)
        bundle.putString("notaSelecionada", viewModel.notaSelecionada.value)
        bundle.putString("anoSelecionado", viewModel.anoSelecionado.value)
        startActivity(Intent(this, MoviesActivity::class.java))
    }
}