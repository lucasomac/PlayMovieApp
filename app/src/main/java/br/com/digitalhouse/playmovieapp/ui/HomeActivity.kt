package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.app_toolbar.*


class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ivLupa.setOnClickListener {
            startActivity(Intent(this, SugestaoActivity::class.java))
        }
        binding.btnPlay.setOnClickListener {
            startActivity(Intent(this, NivelActivity::class.java))
        }
        initToolbar()

        binding.ivSugestao.setOnClickListener {
            startActivity(Intent(this, SugestaoActivity::class.java))
        }
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_settings_24)
            setTitle("")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.action_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean { //Botão adicional na ToolBar
        when (item.getItemId()) {
            R.id.stProfile -> {
                startActivity(Intent(this, InteressesActivity::class.java))
            }
            else -> {
            }
        }
        return true
    }
}