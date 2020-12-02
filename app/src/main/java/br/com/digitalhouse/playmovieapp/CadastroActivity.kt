package br.com.digitalhouse.playmovieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.app_toolbar.*

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)
        setSupportActionBar(findViewById(R.id.material_toolbar))
        supportActionBar?.setTitle("")
        supportActionBar?.setHomeButtonEnabled(true)
//        material_toolbar.setNavigationOnClickListener {
//            finish()
//        }
    }
}