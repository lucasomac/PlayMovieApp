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
    private lateinit var binding: ActivityNiveisBinding
    val viewModel: NivelViewModel by viewModels()
    val adapter = NivelAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvNiveis.adapter = adapter

        viewModel.allLevels.observe(this) {
            adapter.addNiveis(it)
        }

        viewModel.getLevels()

        setSupportActionBar(findViewById(R.id.appBarNivel))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onClickNivel(item: Int) {
        startActivity(Intent(this@NivelActivity, SubNiveisActivity::class.java))
    }
}