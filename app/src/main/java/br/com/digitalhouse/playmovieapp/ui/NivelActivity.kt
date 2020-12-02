package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.NivelAdapter
import br.com.digitalhouse.playmovieapp.domain.Nivel
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

    fun getNiveis(): ArrayList<Nivel> {
        return arrayListOf<Nivel>(
            Nivel(1, 12, 15),
            Nivel(2, 11, 15),
            Nivel(3, 8, 15),
            Nivel(4, 15, 15),
            Nivel(5, 1, 15),
            Nivel(6, 5, 15)
        )
    }

    override fun onClickNivel(item: Int) {
        startActivity(Intent(this@NivelActivity, SubNiveisActivity::class.java))
    }
}