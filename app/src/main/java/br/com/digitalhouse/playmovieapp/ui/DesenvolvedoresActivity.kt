package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.DesenvolvedorAdapter
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor
import br.com.digitalhouse.playmovieapp.ui.viewModel.DesenvolvedoresViewModel
import br.com.digitalhouse.playmovieapp.ui.viewModel.InteressesViewModel
import kotlinx.android.synthetic.main.activity_desenvolvedores.*

class DesenvolvedoresActivity : AppCompatActivity(), DesenvolvedorAdapter.DesenvolvedorListener {

    val viewModel: DesenvolvedoresViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desenvolvedores)

        val adapter = DesenvolvedorAdapter(this)
        rv_desenvolvedores.adapter = adapter

        viewModel.desenvolvedores.observe(this) {
            adapter.addDesenvolvedores(it)
        }

        viewModel.setDesenvolvedores()
    }

    override fun onClickDesenvolvedor(item: Int) {

        var desenvolvedor = viewModel.desenvolvedores.value?.get(item)
        val intent = Intent(this, DetalhesDesenvolvedorActivity::class.java)
        intent.putExtra("desenvolvedor", desenvolvedor)
        startActivity(intent)
    }
}