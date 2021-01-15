package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityCadastroBinding


class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.material_toolbar))
        initToolbar()

        init()
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Criar nova conta")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun init() {
        val htmlAsString = getString(R.string.termos_html)
        binding.textViewTermos.text =
            HtmlCompat.fromHtml(htmlAsString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}