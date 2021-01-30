package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.NivelAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivityNiveisBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.NivelViewModel

class NivelActivity : AppCompatActivity(), NivelAdapter.NivelListener {

    val viewModel: NivelViewModel by viewModels()
    val adapter = NivelAdapter(this)
    private lateinit var binding: ActivityNiveisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNiveisBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_niveis)
        binding.rvNiveis.adapter = adapter

        viewModel.niveis.observe(this) {
            adapter.addNiveis(it)
        }

        viewModel.setNiveis()

        setSupportActionBar(findViewById(R.id.appBarNivel))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onClickNivel(item: Int) {
        startActivity(Intent(this@NivelActivity, SubNiveisActivity::class.java))
    }
}