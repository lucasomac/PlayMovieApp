package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.databinding.ActivityLoginBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.LoginViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var gso: GoogleSignInOptions
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var callbackManager: CallbackManager
    val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        connect()
        setContentView(binding.root)
        callbackManager = CallbackManager.Factory.create()
        binding.buttonLogin.setOnClickListener(this)
        binding.textViewCriarConta.setOnClickListener(this)
        binding.includeSocialNetworksLogin.imageViewGoogle.setOnClickListener(this)
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                handleSignInResult(task);
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            if (account != null) {
                callMain(account.email.toString())
            }
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.statusCode)
            return
        }
    }


    private fun connect() {
        auth = FirebaseAuth.getInstance()
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun updateUI() {
        val account = GoogleSignIn.getLastSignedInAccount(this)
        val currentUser = auth.currentUser
        if (account != null) {
            callMain(account.email.toString())
        } else
            if (currentUser != null) {
                callMain(currentUser.email.toString())
            }
    }

    fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun callRegister() {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    fun callMain(email: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("email", email)
        startActivity(intent)
    }

    fun getDataFieldsLogin() {
        email = binding.textEmail.text.toString()
        password = binding.textPassword.text.toString()
    }

    private fun checaDados(): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }


    fun signIn(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    val emailAddress = firebaseUser.email
                    showMsg("Login realizado com sucesso!")
                    callMain(emailAddress.toString())
                } else {
                    showMsg(task.exception.toString())
                }
            }
    }

    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button_login -> {
                getDataFieldsLogin()
                if (checaDados()) {
                    signIn(email, password)
                } else {
                    showMsg("Preencha todos os dados")
                }
            }
            R.id.imageViewGoogle -> signInGoogle()
            R.id.imageFace -> signInFacebook()
            R.id.textView_criar_conta -> callRegister()
        }
    }

    private fun signInFacebook() {
        // Callback registration
        binding.includeSocialNetworksLogin.imageFace.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    // App code
                }

                override fun onCancel() {
                    // App code
                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })
    }

    companion object {
        private const val TAG = "LOGIN_FIRE"
        private const val RC_SIGN_IN = 9001
    }
}