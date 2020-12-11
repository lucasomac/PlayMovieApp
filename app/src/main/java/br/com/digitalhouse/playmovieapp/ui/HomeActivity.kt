package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.ui.view.ConfiguracoesActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_toolbar.*


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnPlay.setOnClickListener {
            startActivity(Intent(this, NivelActivity::class.java))
        }

        ivSugestao.setOnClickListener {
            startActivity(Intent(this, SugestaoActivity::class.java))
        }

        ivLupa.setOnClickListener {
            startActivity(Intent(this, PesquisaActivity::class.java))
        }

        btn_Configs.setOnClickListener {
            startActivity(Intent(this, ConfiguracoesActivity::class.java))
        }

        btn_Ajustes.setOnClickListener {
            startActivity(Intent(this, InteressesActivity::class.java))
        }
    }
}