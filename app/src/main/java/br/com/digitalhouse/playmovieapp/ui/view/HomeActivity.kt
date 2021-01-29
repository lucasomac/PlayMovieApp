package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Base64
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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.random.Random

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var db: AppDataBase
    private lateinit var repositoryRoom: RepositoryRoom
    private lateinit var binding: ActivityHomeBinding
    private lateinit var auth: FirebaseAuth
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
        auth = FirebaseAuth.getInstance()
        initBanco()
        gerarHashFacebook()
        repositoryRoom = RepositoryRoomImplementation(db.genreDAO())
        binding.btnPlay.setOnClickListener(this)

        binding.ivSugestao.setOnClickListener(this)

        binding.ivLupa.setOnClickListener(this)

        binding.btnConfigs.setOnClickListener(this)

        binding.btnAjustes.setOnClickListener(this)

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

    override fun onStart() {
        super.onStart()
        viewModel.selectGenres()
        updateUI()

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
//            R.id.btnPlay -> startActivity(Intent(this, NivelActivity::class.java))
            R.id.ivSugestao -> openMovieSugestion()
            R.id.ivLupa -> startActivity(Intent(this, PesquisaActivity::class.java))
            R.id.btnConfigs -> startActivity(Intent(this, ConfiguracoesActivity::class.java))
            R.id.btnPlay -> logOut()
            R.id.btnAjustes -> startActivity(Intent(this, InteressesActivity::class.java))
        }
    }

    fun initBanco() {
        db = AppDataBase.getAppDatabase(this)
    }

    private fun updateUI() {
        val currentUserGoogleSignInAccount = GoogleSignIn.getLastSignedInAccount(this)
        val currentUser = auth.currentUser
        val currentUserFacebookAuthCredential = null
        if (currentUserGoogleSignInAccount != null) {
            setFields(
                currentUserGoogleSignInAccount!!.displayName,
                currentUserGoogleSignInAccount.email.toString(),
                currentUserGoogleSignInAccount.photoUrl
            )
        } else {
            if (currentUser != null) {
                setFields(
                    currentUser!!.displayName,
                    currentUser.email.toString(),
                    currentUser.photoUrl
                )
            }
        }
    }

    fun setFields(nome: String? = null, email: String, foto: Uri? = null) {
        if (foto != null) {
            Glide.with(this).asBitmap().load(foto)
                .into(binding.includePerfilResume.ivFotoPerfilResumo)
        }
        if (nome != null) {
            binding.includePerfilResume.tvNomeTelaHome.text = nome
        } else {
            binding.includePerfilResume.tvNomeTelaHome.text = email
        }
    }

    fun gerarHashFacebook() {
        try {
            val info = packageManager.getPackageInfo(
                "br.com.digitalhouse.playmovieapp",  //Insert your own package name.
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
    }

    fun logOut() {
        Firebase.auth.signOut()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}