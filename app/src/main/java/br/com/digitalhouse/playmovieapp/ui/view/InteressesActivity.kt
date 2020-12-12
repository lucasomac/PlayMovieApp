package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.InteresseAdapter
import br.com.digitalhouse.playmovieapp.ui.viewModel.InteressesViewModel
import kotlinx.android.synthetic.main.activity_interesses.*
import kotlinx.android.synthetic.main.app_toolbar.*

class InteressesActivity : AppCompatActivity(), InteresseAdapter.InteresseListener {

    val viewModel: InteressesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interesses)
        initToolbar()

        val adapter = InteresseAdapter(this)
        rv_interesses.adapter = adapter

        viewModel.interesses.observe(this) {
            adapter.addInteresses(it)
        }

        viewModel.setInteresses()
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