package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityLoginBinding
import br.com.digitalhouse.playmovieapp.ui.viewModel.LoginViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var googleSignInClient: GoogleSignInOptions
    private lateinit var auth: FirebaseAuth
    val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build();

//        mGoogleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
//            .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInClient)
//            .build();
        binding.buttonLogin.setOnClickListener(this)
        binding.textViewCriarConta.setOnClickListener(this)
        binding.includeSocialNetworksLogin.imageViewGoogle.setOnClickListener(this)
    }

//    private fun signInGoogle() {
//        Intent signInIntent = Auth . GoogleSignInApi . getSignInIntent (mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun callRegister() {
        val intent = Intent(this, CadastroActivity::class.java)
        startActivity(intent)
    }

    fun getDataFieldsLogin() {
        val email = binding.textEmail.toString()
        val password = binding.textPassword.text.toString()
        val aptEmail = email.isNotEmpty()
        val aptPassword = password.isNotEmpty()
        if (aptEmail && aptPassword) {
            sendDataFirebase(email, password)
        } else {
            showMsg("Preencha todos os dados")
        }
    }

    fun sendDataFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    val key = firebaseUser.uid
                    val email = firebaseUser.email
                    showMsg("Login realizado com sucesso!")
                    callMain(key, email.toString())
                } else {
                    showMsg(task.exception.toString())
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            callMain(user.uid, user.email.toString())
        }
    }


    fun showMsg(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    fun callMain(key: String, email: String) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("key", key)
        intent.putExtra("email", email)
        startActivity(intent)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.button_login -> getDataFieldsLogin()
//            R.id.imageViewGoogle -> signInGoogle()
            R.id.textView_criar_conta -> callRegister()
        }
    }

}