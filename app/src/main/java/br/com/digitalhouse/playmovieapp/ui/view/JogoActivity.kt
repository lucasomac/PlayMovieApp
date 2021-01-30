package br.com.digitalhouse.playmovieapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.digitalhouse.playmovieapp.R
import kotlinx.android.synthetic.main.app_toolbar.*

class JogoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(material_toolbar)
        setTitle(R.string.titulo_jogo) // TODO provisório
    }
}