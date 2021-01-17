package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityJogoBinding

class JogoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJogoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.includeConfigToolbar.materialToolbar)
        setTitle(R.string.titulo_jogo) // TODO provisório
    }
}