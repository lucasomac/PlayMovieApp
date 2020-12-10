package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import kotlinx.android.synthetic.main.form_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnLogin.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        tvCriarConta.setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }
}