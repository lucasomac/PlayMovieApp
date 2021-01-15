package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener {
            startActivity(Intent(this, NivelActivity::class.java))
        }

        binding.ivSugestao.setOnClickListener {
            startActivity(Intent(this, SugestaoActivity::class.java))
        }

        binding.ivLupa.setOnClickListener {
            startActivity(Intent(this, PesquisaActivity::class.java))
        }

        binding.btnConfigs.setOnClickListener {
            startActivity(Intent(this, ConfiguracoesActivity::class.java))
        }

        binding.btnAjustes.setOnClickListener {
            startActivity(Intent(this, InteressesActivity::class.java))
        }
    }
}