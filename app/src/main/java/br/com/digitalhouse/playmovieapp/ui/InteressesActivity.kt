package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.InteresseAdapter
import br.com.digitalhouse.playmovieapp.domain.Interesse
import kotlinx.android.synthetic.main.activity_interesses.*
import kotlinx.android.synthetic.main.app_toolbar.*

class InteressesActivity : AppCompatActivity(), InteresseAdapter.InteresseListener {

    val interesses = arrayListOf<Interesse>(
        Interesse(1, "Sci-fi", true, R.drawable.ic_robot),
        Interesse(2, "Familia", false, R.drawable.ic_people),
        Interesse(3, "Terror", false, R.drawable.ic_ghost),
        Interesse(4, "Ação", false, R.drawable.ic_bomb),
        Interesse(5, "Romance", false, R.drawable.ic__heart),
        Interesse(6, "Documentário", false, R.drawable.ic_book),
        Interesse(7, "Suspense", false, R.drawable.ic_mask),
        Interesse(8, "Quadrinhos", true, R.drawable.ic_comics)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interesses)
        initToolbar()
        val adapter = InteresseAdapter(interesses, this)
        rv_interesses.adapter = adapter

    }

    override fun onClickInteresse(
        isChecked: Boolean,
        interesseIcon: ImageView,
        interesseDesc: TextView
    ) {

        if (isChecked) {
            interesseDesc.setTextColor(ContextCompat.getColor(this, R.color.secondary))
            interesseIcon.setColorFilter(ContextCompat.getColor(this, R.color.secondary))
        } else {
            interesseDesc.setTextColor(ContextCompat.getColor(this, R.color.white))
            interesseIcon.setColorFilter(ContextCompat.getColor(this, R.color.white))

        }
    }

    override fun changeColor(interesseIcon: ImageView, interesseDesc: TextView) {
        interesseDesc.setTextColor(ContextCompat.getColor(this, R.color.secondary))
        interesseIcon.setColorFilter(ContextCompat.getColor(this, R.color.secondary))
    }

    private fun initToolbar() {
        val toolbar = material_toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Interesses")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }
}