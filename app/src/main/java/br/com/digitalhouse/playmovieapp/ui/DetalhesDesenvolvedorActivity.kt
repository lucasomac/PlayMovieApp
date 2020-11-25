package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor
import kotlinx.android.synthetic.main.activity_detalhes_desenvolvedor.*
import java.lang.Exception

class DetalhesDesenvolvedorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_desenvolvedor)

        val extras = intent.extras
        try {
            val desenvolvedor: Desenvolvedor = extras?.get("desenvolvedor") as Desenvolvedor
            desenvolvedorFoto_detalhes.setImageResource(desenvolvedor.foto)
            desenvolvedorNome_detalhes.text = desenvolvedor.nome
            desenvolvedorIdade_detalhes.text = desenvolvedor.idade.toString() + " anos"
            desenvolvedorMiniCv_detalhes.text = desenvolvedor.miniCv

        } catch (exception: Exception) {
            Log.e("DetalhesDesenvolvedorActivity", exception.toString())
        }

        desenvolvedorAppBar.setNavigationOnClickListener {
            finish()
        }
    }
}