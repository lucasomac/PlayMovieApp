package br.com.digitalhouse.playmovieapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.adapters.NivelResumeAdapter
import br.com.digitalhouse.playmovieapp.domain.Nivel
import br.com.digitalhouse.playmovieapp.domain.Resumo
import br.com.digitalhouse.playmovieapp.domain.ResumoAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.layout_slide_home_niveis.*

class HomeActivity : AppCompatActivity(), NivelResumeAdapter.NivelResumeListener {
    lateinit var list: ArrayList<Resumo>
    lateinit var adapter: ResumoAdapter
    val adapterResume = NivelResumeAdapter(getNiveis(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnPlay.setOnClickListener {
            startActivity(Intent(this, NivelActivity::class.java))
        }

        list = getResumos()
        adapter = ResumoAdapter(this, list)
        vpHome.adapter = adapter
        rvNiveisResume.adapter = adapterResume
    }

    fun getResumos(): ArrayList<Resumo> {
        return arrayListOf(
            Resumo(R.layout.layout_slide_home_filme_dia),
            Resumo(R.layout.layout_slide_home_niveis)
        )
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

    override fun onClickNivelResume() {
        TODO("Not yet implemented")
    }
}