package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.adapters.SubNivelAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivitySubNiveisBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.NivelViewModel

class SubNiveisActivity : AppCompatActivity(), SubNivelAdapter.SubNivelListner {
    private lateinit var binding: ActivitySubNiveisBinding
    private lateinit var nivel: String
    val adapter = SubNivelAdapter(this)
    val viewModel: NivelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubNiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        nivel = bundle?.get("nivel").toString()
        Log.i("LEVLE", nivel)
        binding.gridViewSubNiveis.adapter = adapter
        viewModel.allQuestionsFiltered.observe(this) {
            adapter.addQuestion(it)
        }
//        viewModel.questionsFiltered(nivel.toInt())
        viewModel.startSubNivel(nivel.toInt())
        initToolbar()
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("Nível $nivel")
    }

    override fun onClickNivel(item: Int) {
        val intentGame = Intent(this, JogoActivity::class.java).apply {
            putExtra("numero", item)
            putExtra("question", adapter.getIdQuestion(item))
        }
        startActivity(intentGame)
    }
}