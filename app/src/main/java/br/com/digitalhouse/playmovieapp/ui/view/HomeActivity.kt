package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.database.AppDataBase
import br.com.digitalhouse.playmovieapp.databinding.ActivityHomeBinding
import br.com.digitalhouse.playmovieapp.services.RepositoryRoom
import br.com.digitalhouse.playmovieapp.services.RepositoryRoomImplementation
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.HomeActivityViewModel

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var db: AppDataBase
    private lateinit var repositoryRoom: RepositoryRoom
    private lateinit var binding: ActivityHomeBinding
    val viewModel by viewModels<HomeActivityViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return HomeActivityViewModel(repositoryRoom, repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanco()
        repositoryRoom = RepositoryRoomImplementation(db.genreDAO())
        binding.btnPlay.setOnClickListener(this)

        binding.ivSugestao.setOnClickListener(this)

        binding.ivLupa.setOnClickListener(this)

        binding.btnConfigs.setOnClickListener(this)

        binding.btnAjustes.setOnClickListener(this)

        viewModel.genres.observe(this) {
            viewModel.discoveryMovies(
                1,
                it.map { it.id }.subList(0, it.size / 2).toString(),
                "",
                ""
            )
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.selectGenres()
    }

    fun openMovieSugestion() {
//        viewModel.selectGenres()
//        var id = 0
//        Log.i("MID", viewModel.movie.value.toString())
//        if (viewModel.movie.value != null)
//            id = viewModel.movie.value!!.id
//        else
//            id = 405965
        val intent = Intent(
            this,
            DetalhesActivityMovie::class.java
        ).apply {
            putExtra("idMovie", viewModel.movie.value!!.id)
//            putExtra("idMovie", id)
        }
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnPlay -> startActivity(Intent(this, NivelActivity::class.java))
            R.id.ivSugestao -> openMovieSugestion()
            R.id.ivLupa -> startActivity(Intent(this, PesquisaActivity::class.java))
            R.id.btnConfigs -> startActivity(Intent(this, ConfiguracoesActivity::class.java))
            R.id.btnAjustes -> startActivity(Intent(this, InteressesActivity::class.java))
        }
    }

    fun initBanco() {
        db = AppDataBase.getAppDatabase(this)
    }

    fun updateUI() {

    }
//    fun callLogin() {
//        val intent = Intent(this, LoginActivity::class.java)
//        logOut()
//        finish()
//        startActivity(intent)
//    }

//    fun logOut() {
//        Firebase.auth.signOut()
//    }
}