package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor
import br.com.digitalhouse.playmovieapp.ui.viewModel.DetalhesDesenvolvedorViewModel
import kotlinx.android.synthetic.main.activity_detalhes_desenvolvedor.*
import java.lang.Exception

class DetalhesDesenvolvedorActivity : AppCompatActivity() {

    val viewModel: DetalhesDesenvolvedorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_desenvolvedor)

        try {
            viewModel.setDesenvolvedor(intent.extras?.get("desenvolvedor") as Desenvolvedor)
            desenvolvedorFoto_detalhes.setImageResource(viewModel.desenvolvedor.value!!.foto)
            desenvolvedorNome_detalhes.text = viewModel.desenvolvedor.value?.nome
            desenvolvedorIdade_detalhes.text = viewModel.desenvolvedor.value?.idade.toString() + " anos"
            desenvolvedorMiniCv_detalhes.text = viewModel.desenvolvedor.value?.miniCv

        } catch (exception: Exception) {
            Log.e("DetalhesDesenvolvedorActivity", exception.toString())
        }

        desenvolvedorAppBar.setNavigationOnClickListener {
            finish()
        }
    }
}