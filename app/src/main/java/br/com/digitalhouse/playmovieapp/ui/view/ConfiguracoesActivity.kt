package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.ConfiguracaoAdapter
import br.com.digitalhouse.playmovieapp.databinding.ActivityConfiguracoesBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.ConfiguracoesViewModel
import com.google.android.material.snackbar.Snackbar

class ConfiguracoesActivity : AppCompatActivity() {
    private val viewModel: ConfiguracoesViewModel by viewModels()
    private lateinit var binding: ActivityConfiguracoesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfiguracoesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        initRecyclerConfiguracoes()
        initAlertaPoliticaPrivacidade()
    }


    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Configurações"
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun initRecyclerConfiguracoes() {
        val listaConfiguracoes = viewModel.getConfiguracoes()
        val recycler = binding.rvConfiguracoes
        recycler.adapter = ConfiguracaoAdapter(listaConfiguracoes)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
    }

    private fun initAlertaPoliticaPrivacidade() {
        Snackbar.make(
            binding.constraintLayoutConfig,
            R.string.politica_de_privacidade,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction("Visualizar") {
                Toast.makeText(
                    applicationContext,
                    "Vai abrir um dialog com os nossos termos",
                    Toast.LENGTH_LONG
                ).show()
            }
            .show()
    }
}