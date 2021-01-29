package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.databinding.ActivitySplashBinding
import br.com.digitalhouse.playmovieapp.ui.novos.Login

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        //TODO alterar para finalizar apenas depois do carregamento inicial
        binding.imageViewLogo.alpha = 0f
        binding.imageViewLogo.animate().setDuration(1500).alpha(1f).withEndAction {
            startActivity(Intent(this@SplashActivity, Login::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}