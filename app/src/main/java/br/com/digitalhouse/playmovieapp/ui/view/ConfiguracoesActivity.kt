package br.com.digitalhouse.playmovieapp.ui.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.ConfiguracaoAdapter
import br.com.digitalhouse.playmovieapp.ui.viewModel.ConfiguracoesViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_configuracoes.*
import kotlinx.android.synthetic.main.app_toolbar.*

class ConfiguracoesActivity : AppCompatActivity() {
    private val viewModel: ConfiguracoesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        initToolbar()
        initRecyclerConfiguracoes()
        initAlertaPoliticaPrivacidade()
    }

    private fun initToolbar() {
        val toolbar = material_toolbar
        setSupportActionBar(toolbar)
        setTitle(R.string.titulo_configuracoes)
    }

    private fun initRecyclerConfiguracoes() {
        val listaConfiguracoes = viewModel.getConfiguracoes()
        val recycler = rv_configuracoes
        recycler.adapter = ConfiguracaoAdapter(listaConfiguracoes)

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
    }

    private fun initAlertaPoliticaPrivacidade() {
        Snackbar.make(
            constraintLayout_config,
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