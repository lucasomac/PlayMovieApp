package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.DesenvolvedorAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivityDesenvolvedoresBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.DesenvolvedoresViewModel

class DesenvolvedoresActivity : AppCompatActivity(), DesenvolvedorAdapter.DesenvolvedorListener {

    val viewModel: DesenvolvedoresViewModel by viewModels()
    private lateinit var binding: ActivityDesenvolvedoresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDesenvolvedoresBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_desenvolvedores)

        val adapter = DesenvolvedorAdapter(this)
        binding.rvDesenvolvedores.adapter = adapter

        viewModel.desenvolvedores.observe(this) {
            adapter.addDesenvolvedores(it)
        }

        viewModel.setDesenvolvedores()
    }

    override fun onClickDesenvolvedor(item: Int) {

        var desenvolvedor = viewModel.desenvolvedores.value?.get(item)
        val intent = Intent(this, DetalhesDesenvolvedorActivity::class.java)
        intent.putExtra("desenvolvedor", desenvolvedor)
        startActivity(intent)
    }
}