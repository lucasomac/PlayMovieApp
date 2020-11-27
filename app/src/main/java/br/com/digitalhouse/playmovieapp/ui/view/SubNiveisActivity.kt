package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.DesenvolvedorAdapter
import br.com.digitalhouse.playmovieapp.adapters.SubNivelAdapter
import br.com.digitalhouse.playmovieapp.domain.SubNivel
import br.com.digitalhouse.playmovieapp.ui.DetalhesDesenvolvedorActivity
import kotlinx.android.synthetic.main.activity_sub_niveis.*
import kotlinx.android.synthetic.main.app_toolbar.*

class SubNiveisActivity : AppCompatActivity(), SubNivelAdapter.SubNivelListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_niveis)

        initToolbar()
        setValuesGridView()
    }

    private fun initToolbar() {
        setSupportActionBar(material_toolbar)
        // TODO deve ser dinâmico provisório
        setTitle("") // para não pegar o padrão
        textView_title_toolbar_custom.text = "Nível 01"
    }

    private fun setValuesGridView() {
        var listaSubNiveis: ArrayList<SubNivel> = arrayListOf()

        for (i in 1..15) {
            listaSubNiveis.add(
                SubNivel(
                    99,
                    1,
                    i < 5,
                    R.drawable.sample_filme_cover
                )
            )
        }

        val adapterSubNivel = SubNivelAdapter(listaSubNiveis, this)
        recyclerView_grid_sub_niveis.adapter = adapterSubNivel
    }

    override fun onClickListener(item: Int) {
        val intent = Intent(this@SubNiveisActivity, JogoActivity::class.java)
        //intent.putExtra("EXTRA_SUBNIVEL", restaurant.dishes[position])
        startActivity(intent)
    }
}