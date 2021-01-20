package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.viewinterop.viewModel
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.InteresseAdapter
import br.com.digitalhouse.playmovieapp.database.AppDataBase
import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.services.RepositoryRoom
import br.com.digitalhouse.playmovieapp.services.RepositoryRoomImplementation
import br.com.digitalhouse.playmovieapp.ui.viewModel.InteressesViewModel
import kotlinx.android.synthetic.main.activity_interesses.*
import kotlinx.android.synthetic.main.app_toolbar.*

class InteressesActivity : AppCompatActivity(), InteresseAdapter.InteresseListener {
    private lateinit var db: AppDataBase
    private lateinit var repositoryRoom: RepositoryRoom

    val viewModel by viewModels<InteressesViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return InteressesViewModel(repositoryRoom) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interesses)
        initBanco()
        repositoryRoom = RepositoryRoomImplementation(db.genreDAO())
        initToolbar()
        val adapter = InteresseAdapter(this)
        rv_interesses.adapter = adapter

        viewModel.interesses.observe(this) {
            adapter.addInteresses(it)
        }

        viewModel.genres.observe(this) {
            viewModel.activeGenres()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.selectGenres()
    }

    override fun onClickInteresse(
        isChecked: Boolean,
        interesseIcon: ImageView,
        interesseDesc: TextView,
        genre: Genre
    ) {
        if (isChecked) {
            interesseDesc.setTextColor(ContextCompat.getColor(this, R.color.secondary))
            interesseIcon.setColorFilter(ContextCompat.getColor(this, R.color.secondary))
            viewModel.addNewGenre(genre)
//            viewModel.selectGenres()
        } else {
            interesseDesc.setTextColor(ContextCompat.getColor(this, R.color.white))
            interesseIcon.setColorFilter(ContextCompat.getColor(this, R.color.white))
            viewModel.delGenre(genre)
//            viewModel.selectGenres()
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

    fun initBanco() {
        db = AppDataBase.getAppDatabase(this)
    }
}