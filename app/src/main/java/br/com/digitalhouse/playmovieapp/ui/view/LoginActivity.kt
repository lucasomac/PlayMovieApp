package br.com.digitalhouse.playmovieapp.ui.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityLoginBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val GOOGLE_SIGN_IN = 100
    private var callbackManager = CallbackManager.Factory.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        session()
    }

    private fun session() {
        val prefs =
            getSharedPreferences(R.string.prefs_file.toString(), Context.MODE_PRIVATE)
        val nome = prefs.getString("nome", null)
        val email = prefs.getString("email", null)
        val urlPhoto = prefs.getString("urlPhoto", null)
        val provider = prefs.getString("provider", null)
        if (email != null && provider != null) {
            showHome(email, nome.toString(), urlPhoto.toString(), provider.toString())
        }
    }

    fun setup() {
        binding.textViewCriarConta.setOnClickListener {
            showRegister()
        }
        binding.buttonLogin.setOnClickListener {
            if (checaCampos()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.textEmail.text.toString(),
                    binding.textPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(
                            it.result?.user?.email.toString(),
                            it.result?.user?.displayName.toString(),
                            it.result?.user?.photoUrl.toString(),
                            ProviderTypes.BASIC.name
                        )
                    } else {
                        showAlert()
                    }
                }
            }
        }
        binding.includeSocialNetworksLogin.imageViewGoogle.setOnClickListener {
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)
        }
        binding.includeSocialNetworksLogin.imageFace.setOnClickListener {
            LoginManager.getInstance()
                .logInWithReadPermissions(this, listOf("public_profile", "email"))
            LoginManager.getInstance()
                .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken
                            val credential =
                                FacebookAuthProvider.getCredential(token.token)
                            FirebaseAuth.getInstance().signInWithCredential(credential)
                                .addOnCompleteListener {
                                    if (it.isSuccessful) {
                                        showHome(
                                            it.result?.user?.email.toString(),
                                            it.result?.user?.displayName.toString(),
                                            it.result?.user?.photoUrl.toString(),
                                            ProviderTypes.FACEBOOK.name
                                        )
                                    } else {
                                        showAlert()
                                    }
                                }
                        }
                    }

                    override fun onCancel() {
                    }

                    override fun onError(error: FacebookException?) {
                        showAlert()
                    }

                })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                showHome(
                                    account.email.toString(),
                                    account.displayName.toString(),
                                    account.photoUrl.toString(),
                                    ProviderTypes.GOOGLE.name
                                )
                            } else {
                                showAlert()
                            }
                        }
                }
            } catch (e: ApiException) {
                showAlert()
            }
        }
    }

    fun checaCampos(): Boolean {
        return binding.textEmail.text!!.isNotEmpty()
                && binding.textPassword.text!!.isNotEmpty()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(
            "Error"
        )
        builder.setMessage("Erro ao tentar logar!")
        builder.setPositiveButton(
            "Aceitar", null
        )
        val dialog = builder.create()
        dialog.show()
    }

    fun showHome(email: String, nome: String, urlPhoto: String, provider: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("nome", nome)
            putExtra("urlPhoto", urlPhoto)
            putExtra("provider", provider)
        }
        startActivity(homeIntent)
    }

    fun showRegister() {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }
}