package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.SubNivelAdapter
import br.com.digitalhouse.playmovieapp.domain.SubNivel
import kotlinx.android.synthetic.main.activity_sub_niveis.*
import kotlinx.android.synthetic.main.app_toolbar.*

class SubNiveisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub_niveis)

        initToolbar()
        setValuesGridView()
    }

    private fun initToolbar() {
        val toolbar = material_toolbar
        setSupportActionBar(toolbar)
        setTitle(R.string.titulo_sub_nivel) // TODO provisório
    }

    private fun setValuesGridView() {
        var listaSubNiveis: ArrayList<SubNivel> = arrayListOf()

        for (i in 1..15) {
            listaSubNiveis.add(
                SubNivel(
                    99,
                    1,
                    i % 2 == 0,
                    R.drawable.sample_filme_cover
                )
            )
        }

        val adapterSubNivel = SubNivelAdapter(this@SubNiveisActivity, listaSubNiveis)
        gridViewSubNiveis.adapter = adapterSubNivel

        gridViewSubNiveis.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this@SubNiveisActivity, JogoActivity::class.java)
//                intent.putExtra("EXTRA_DISH", restaurant.dishes[position])
                startActivity(intent)
            }
    }
}