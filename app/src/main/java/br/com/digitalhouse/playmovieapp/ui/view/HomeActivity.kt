package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityHomeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPlay.setOnClickListener(this)

        binding.ivSugestao.setOnClickListener(this)

        binding.ivLupa.setOnClickListener(this)

        binding.btnConfigs.setOnClickListener(this)

        binding.btnAjustes.setOnClickListener(this)
    }

    fun callLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        logOut()
        finish()
        startActivity(intent)
    }

    fun logOut() {
        Firebase.auth.signOut()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnPlay -> startActivity(Intent(this, NivelActivity::class.java))
            R.id.ivSugestao -> startActivity(Intent(this, SugestaoActivity::class.java))
            R.id.ivLupa -> startActivity(Intent(this, PesquisaActivity::class.java))
            R.id.btnConfigs -> startActivity(Intent(this, ConfiguracoesActivity::class.java))
            R.id.btnAjustes -> startActivity(Intent(this, InteressesActivity::class.java))
        }
    }
}