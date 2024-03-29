package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.NivelAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivityNiveisBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.NivelViewModel

class NivelActivity : AppCompatActivity(), NivelAdapter.NivelListener {
    private lateinit var binding: ActivityNiveisBinding
    private lateinit var email: String
    val adapter = NivelAdapter(this)
    val viewModel: NivelViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNiveisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = getSharedPreferences(R.string.prefs_file.toString(), Context.MODE_PRIVATE)
        email = prefs.getString("email", null).toString()

        binding.rvNiveis.adapter = adapter
        viewModel.allLevels.observe(this) {
            adapter.addNiveis(it)
        }
        viewModel.start(email)
        initToolbar()
    }

    override fun onResume() {
        super.onResume()
        viewModel.start(email)
    }

    override fun onStart() {
        super.onStart()
        viewModel.start(email)
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.appBarNivel))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClickNivel(item: Int) {
        val intent = Intent(this, SubNiveisActivity::class.java).apply {
            putExtra("nivel", adapter.getNivel(item))
        }
        startActivity(intent)
    }
}