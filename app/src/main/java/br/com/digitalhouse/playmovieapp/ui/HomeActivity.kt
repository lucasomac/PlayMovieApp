package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.NivelResumeAdapter
import br.com.digitalhouse.playmovieapp.domain.Nivel
import br.com.digitalhouse.playmovieapp.domain.Resumo
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), NivelResumeAdapter.NivelResumeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btnPlay.setOnClickListener {
            startActivity(Intent(this, NivelActivity::class.java))
        }
        //        getSupportActionBar()
        setSupportActionBar(findViewById(R.id.appBarHome))
        supportActionBar?.setTitle("")
        supportActionBar?.setHomeButtonEnabled(true)

//        ivLupa.setOnClickListener {
//            startActivity(Intent(this, SugestaoActivity::class.java))
//        }

        ivSugestao.setOnClickListener {
            startActivity(Intent(this, SugestaoActivity::class.java))
        }
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

//    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
//        when (item.getItemId()) {
//            R.id.stProfile -> {
//                startActivity(
//                    Intent(
//                        this,
//                        InteressesActivity::class.java
//                    )
//                ) //O efeito ao ser pressionado do botão (no caso abre a activity)
//                finishAffinity() //Método para matar a activity e não deixa-lá indexada na pilhagem
//            }
//            else -> {
//            }
//        }
//        return true
//    }
}