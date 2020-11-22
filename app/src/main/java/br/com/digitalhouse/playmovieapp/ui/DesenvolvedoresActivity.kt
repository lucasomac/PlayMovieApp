package br.com.digitalhouse.playmovieapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.DesenvolvedorAdapter
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor
import kotlinx.android.synthetic.main.activity_desenvolvedores.*

class DesenvolvedoresActivity : AppCompatActivity(), DesenvolvedorAdapter.DesenvolvedorListener {

    val desenvolvedores = arrayListOf<Desenvolvedor>(
        Desenvolvedor(
            1,
            "Camila Pelegrina",
            "176 xícaras de café",
            23,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            R.drawable.perfil
        ),
        Desenvolvedor(
            2,
            "Danilo Augusto",
            "789 xícaras de café",
            23,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            R.drawable.perfil
        ),
        Desenvolvedor(
            3,
            "Lucas Oliveira",
            "462 xícaras de café",
            23,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            R.drawable.perfil
        ),
        Desenvolvedor(
            4,
            "Rodrigo Felipe",
            "178 xícaras de café",
            23,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            R.drawable.perfil
        ),
        Desenvolvedor(
            4,
            "Sarah Carneiro",
            "498 xícaras de café",
            23,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            R.drawable.perfil
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desenvolvedores)

        val adapter = DesenvolvedorAdapter(desenvolvedores, this)
        rv_desenvolvedores.adapter = adapter
    }

    override fun onClickDesenvolvedor(item: Int) {

        var desenvolvedor = desenvolvedores.get(item)
        val intent = Intent(this, DetalhesDesenvolvedorActivity::class.java)
        intent.putExtra("desenvolvedor", desenvolvedor)
        startActivity(intent)
    }
}