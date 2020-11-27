package br.com.digitalhouse.playmovieapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.digitalhouse.playmovieapp.R
import kotlinx.android.synthetic.main.app_toolbar.*

class JogoActivity : AppCompatActivity() {
    private lateinit var currentLevel: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo)

        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(material_toolbar)
        setTitle("")
        textView_title_toolbar_custom.text = "Pergunta 1 de 15"
    }
}