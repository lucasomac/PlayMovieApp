package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.databinding.ActivityDetalhesDesenvolvedorBinding
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor
import br.com.digitalhouse.playmovieapp.ui.viewModel.DetalhesDesenvolvedorViewModel

class DetalhesDesenvolvedorActivity : AppCompatActivity() {

    val viewModel: DetalhesDesenvolvedorViewModel by viewModels()
    private lateinit var binding: ActivityDetalhesDesenvolvedorBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesDesenvolvedorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            viewModel.setDesenvolvedor(intent.extras?.get("desenvolvedor") as Desenvolvedor)
            binding.desenvolvedorFotoDetalhes.setImageResource(viewModel.desenvolvedor.value!!.foto)
            binding.desenvolvedorNomeDetalhes.text = viewModel.desenvolvedor.value?.nome
            binding.desenvolvedorIdadeDetalhes.text =
                "${viewModel.desenvolvedor.value?.idade.toString()} anos"
            binding.desenvolvedorMiniCvDetalhes.text = viewModel.desenvolvedor.value?.miniCv

        } catch (exception: Exception) {
            Log.e("DetalhesDesenvolvedorActivity", exception.toString())
        }

        binding.desenvolvedorAppBar.setNavigationOnClickListener {
            finish()
        }
    }
}