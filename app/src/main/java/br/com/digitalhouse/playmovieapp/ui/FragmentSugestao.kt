package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import br.com.digitalhouse.playmovieapp.R

class FragmentSugestao : Fragment() {

    private lateinit var generoSelecionado: String
    private lateinit var categoriaSelecionada: String
    private lateinit var notaSelecionada: String
    private lateinit var anoSelecionado: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sugestao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Spinner>(R.id.spinnerGeneros).apply {
            val generos = resources.getStringArray(R.array.generos)
            this.adapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, generos)
            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    generoSelecionado = generos[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Spinner>(R.id.spinnerCategorias).apply {
            val categorias = resources.getStringArray(R.array.categorias)
            this.adapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, categorias)
            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    categoriaSelecionada = categorias[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Spinner>(R.id.spinnerNota).apply {
            val notas = resources.getStringArray(R.array.notas)
            this.adapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, notas)
            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    notaSelecionada = notas[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }

        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Spinner>(R.id.spinnerAno).apply {
            val anos = resources.getStringArray(R.array.anos)
            this.adapter =
                ArrayAdapter(view.context, android.R.layout.simple_spinner_dropdown_item, anos)
            this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    anoSelecionado = anos[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
            }
        }
        view.findViewById<AppCompatButton>(R.id.btnSugestao).setOnClickListener(openMovieDetail())
    }

    private fun openMovieDetail(): View.OnClickListener? = View.OnClickListener {
        val bundle = Bundle()
        bundle.putString("generoSelecionado", generoSelecionado)
        bundle.putString("categoriaSelecionada", categoriaSelecionada)
        bundle.putString("notaSelecionada", notaSelecionada)
        bundle.putString("anoSelecionado", anoSelecionado)
//        val fragMovieDetail = fragmentMovieDetail()
//        fragMovieDetail.arguments = bundle
//        SugestaoActivity.fm.beginTransaction().replace(R.id.fragmentContainerSugestao, fragMovieDetail, null)
//            .addToBackStack(null).commit()
    }

}