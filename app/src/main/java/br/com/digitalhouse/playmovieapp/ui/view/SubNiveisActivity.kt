package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.SubNivelAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivitySubNiveisBinding
import br.com.digitalhouse.playmovieapp.domain.SubNivel

class SubNiveisActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubNiveisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubNiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initToolbar()
        setValuesGridView()
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
        binding.gridViewSubNiveis.adapter = adapterSubNivel

        binding.gridViewSubNiveis.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(this@SubNiveisActivity, JogoActivity::class.java)
//                intent.putExtra("EXTRA_DISH", restaurant.dishes[position])
                startActivity(intent)
            }
    }
}