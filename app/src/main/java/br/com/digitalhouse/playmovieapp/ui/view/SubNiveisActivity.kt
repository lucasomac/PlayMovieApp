package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.adapters.SubNivelAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivitySubNiveisBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.NivelViewModel

class SubNiveisActivity : AppCompatActivity(), SubNivelAdapter.SubNivelListner {
    private lateinit var binding: ActivitySubNiveisBinding
    private lateinit var nivel : String
    val adapter = SubNivelAdapter(this)
    val viewModel: NivelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubNiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        nivel = bundle?.get("nivel") as String

        initToolbar()

        binding.gridViewSubNiveis.adapter = adapter
        viewModel.allQuestionsFiltered.observe(this) {
            adapter.addQuestion(it)
        }

        viewModel.questionsFiltered(nivel.toInt())
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("Nível $nivel")
    }

    private fun setValuesGridView() {

//        binding.rvNiveis.adapter = adapter
//
//        viewModel.allQuestionsFiltered.observe(this) {
//            adapter.addNiveis(it)
//        }
//
//        viewModel.questionsFiltered(nivel.toInt())
//
//        val adapterSubNivel = SubNivelAdapter(this@SubNiveisActivity, listaSubNiveis)
//        binding.gridViewSubNiveis.adapter = adapterSubNivel
//
//        binding.gridViewSubNiveis.onItemClickListener =
//            AdapterView.OnItemClickListener { parent, view, position, id ->
//                val intent = Intent(this@SubNiveisActivity, JogoActivity::class.java)
////                intent.putExtra("EXTRA_DISH", restaurant.dishes[position])
//                startActivity(intent)
//            }
    }

    override fun onClickNivel(item: Int) {
        TODO("Not yet implemented")
    }
}