package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.NivelAdapter
import br.com.digitalhouse.playmovieapp.getNiveis
import br.com.digitalhouse.playmovieapp.ui.view.SubNiveisActivity
import kotlinx.android.synthetic.main.activity_niveis.*

class NivelActivity : AppCompatActivity(), NivelAdapter.NivelListener {
    val adapter = NivelAdapter(getNiveis(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_niveis)
        rvNiveis.adapter = adapter
        setSupportActionBar(findViewById(R.id.appBarNivel))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onClickNivel(item: Int) {
        startActivity(Intent(this@NivelActivity, SubNiveisActivity::class.java))
    }
}