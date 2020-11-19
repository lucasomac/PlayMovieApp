package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.InteresseAdapter
import br.com.digitalhouse.playmovieapp.domain.Interesse
import kotlinx.android.synthetic.main.activity_interesses.*
import kotlinx.android.synthetic.main.item_interesse.*

class InteressesActivity : AppCompatActivity(), InteresseAdapter.OnClickInteresseListener {

    val interesses = arrayListOf<Interesse>(
        Interesse(1, "Sci-fi", false, R.drawable.ic_robot),
        Interesse(2, "Familia", false, R.drawable.ic_people),
        Interesse(3, "Terror", false, R.drawable.ic_ghost),
        Interesse(4, "Ação", false, R.drawable.ic_bomb),
        Interesse(5, "Romance", false, R.drawable.ic__heart),
        Interesse(6, "Documentário", false, R.drawable.ic_book),
        Interesse(7, "Suspense", false, R.drawable.ic_mask),
        Interesse(8, "Quadrinhos", false, R.drawable.ic_robot)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interesses)

        val adapter = InteresseAdapter(interesses, this)
        rv_interesses.adapter = adapter

    }

    override fun onClickInteresse(position: Int) {
        var interesse = interesses.get(position)
        Toast.makeText(this,interesse.descricao, Toast.LENGTH_SHORT).show()
    }
}