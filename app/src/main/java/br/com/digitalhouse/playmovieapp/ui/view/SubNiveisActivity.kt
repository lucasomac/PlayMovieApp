package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.SubNivelAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivitySubNiveisBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.NivelViewModel
import br.com.digitalhouse.playmovieapp.ui.viewModel.SubNivelViewModel

class SubNiveisActivity : AppCompatActivity(), SubNivelAdapter.SubNivelListner {
    private lateinit var binding: ActivitySubNiveisBinding
    private lateinit var nivel: String
    private lateinit var email: String
    val adapter = SubNivelAdapter(this)
    val viewModel: SubNivelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubNiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        nivel = bundle?.get("nivel").toString()

        binding.gridViewSubNiveis.adapter = adapter
        viewModel.allQuestionsFiltered.observe(this) {
            adapter.addSubNivel(it)
        }

        //viewModel = ViewModelProvider(this).get(NivelViewModel::class.java)

        val prefs = getSharedPreferences(R.string.prefs_file.toString(), Context.MODE_PRIVATE)
        email = prefs.getString("email", null).toString()

        viewModel.start(nivel, email)
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
            putExtra("question", adapter.getIdSubNivel(item))
        }
        startActivity(intentGame)
    }
}