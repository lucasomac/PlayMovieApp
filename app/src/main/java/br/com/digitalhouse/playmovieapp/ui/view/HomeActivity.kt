package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Context
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
import com.bumptech.glide.Glide
import com.facebook.login.LoginManager
import com.google.firebase.auth.FirebaseAuth
import kotlin.random.Random

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var db: AppDataBase
    private lateinit var repositoryRoom: RepositoryRoom
    private lateinit var binding: ActivityHomeBinding
    private lateinit var provider: String
    private lateinit var email: String
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
        val bundle = intent.extras
        val nome = bundle?.get("nome").toString()
        email = bundle?.get("email").toString()
        val urlPhoto = bundle?.get("urlPhoto").toString()
        provider = bundle?.get("provider").toString()
        setup(nome, email, urlPhoto, provider)
        val prefs =
            getSharedPreferences(R.string.prefs_file.toString(), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("nome", nome)
        prefs.putString("urlPhoto", urlPhoto)
        prefs.putString("provider", provider)
        prefs.apply()
        initBanco()
        repositoryRoom = RepositoryRoomImplementation(db.genreDAO())

        binding.btnPlay.setOnClickListener(this)

        binding.ivSugestao.setOnClickListener(this)

        binding.ivLupa.setOnClickListener(this)

//        binding.btnConfigs.setOnClickListener(this)

        binding.btnAjustes.setOnClickListener(this)
        viewModel.pontuacao.observe(this) {
            binding.includePerfilResume.tvPontosTelaHome.text = "${it} pontos"
        }
        viewModel.getPontuacao(email)
        viewModel.genres.observe(this) {
            if (it.size > 0)
                viewModel.discoveryMovies(
                    1,
                    it.map { it.id }[Random.nextInt(0, it.size)].toString(),
                    "",
                    ""
                )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPontuacao(email)
    }

    fun setup(email: String, nome: String, urlPhoto: String, provider: String) {
        Log.i("NOME", nome)
        setFields(email, nome, urlPhoto)
        binding.btnPlay.setOnClickListener(this)
        binding.btnLogoff.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        viewModel.selectGenres()
    }

    fun openMovieSugestion() {
        val intent = Intent(
            this,
            DetalhesActivityMovie::class.java
        ).apply {
            putExtra("idMovie", viewModel.movie.value!!.id)
        }
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnLogoff -> {
                val prefs =
                    getSharedPreferences(
                        R.string.prefs_file.toString(),
                        Context.MODE_PRIVATE
                    ).edit()
                prefs.clear()
                prefs.apply()
                if (provider.equals("facebook", true)) {
                    LoginManager.getInstance().logOut()
                }
                FirebaseAuth.getInstance().signOut()
                onBackPressed()
            }
            R.id.btnPlay -> startActivity(Intent(this, NivelActivity::class.java))
            R.id.ivSugestao -> openMovieSugestion()
            R.id.ivLupa -> startActivity(Intent(this, PesquisaActivity::class.java))
//            R.id.btnConfigs -> startActivity(Intent(this, ConfiguracoesActivity::class.java))
            R.id.btnAjustes -> startActivity(Intent(this, InteressesActivity::class.java))
        }
    }

    fun initBanco() {
        db = AppDataBase.getAppDatabase(this)
    }

    fun setFields(nome: String, email: String, foto: String) {
        Log.i("NOMES", nome)
        if (foto != "null") {
            Glide.with(this).asBitmap().load(foto)
                .into(binding.includePerfilResume.ivFotoPerfilResumo)
        }
        if (nome != "null") {
            binding.includePerfilResume.tvNomeTelaHome.text = nome
        }
    }
}

enum class ProviderTypes() {
    BASIC,
    GOOGLE,
    FACEBOOK
}