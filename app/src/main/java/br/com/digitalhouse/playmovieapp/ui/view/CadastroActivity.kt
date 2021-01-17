package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(findViewById(R.id.material_toolbar))
        initToolbar()
        init()
        binding.buttonCriarConta.setOnClickListener {
            getDataFields()
        }
    }

    private fun initToolbar() {
        val toolbar = binding.includeConfigToolbar.materialToolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Criar nova conta")
        supportActionBar?.setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        supportActionBar?.setHomeButtonEnabled(true)
    }

    private fun init() {
        val htmlAsString = getString(R.string.termos_html)
        binding.textViewTermos.text =
            HtmlCompat.fromHtml(htmlAsString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    //Captura os dado sidgitados nos campos
    fun getDataFields() {
        val email = binding.textEmail.text.toString()
        val password = binding.textPassword.text.toString()
        val passwordConfirm = binding.textPasswordConfirm.text.toString()
        val aptEmail = email.isNotEmpty()
        val aptPassword = password.isNotEmpty()
//        val aptPasswordConfirm = passwordConfirm.isNotEmpty()
        if (aptEmail && aptPassword && password.equals(passwordConfirm)) {
            sendDataFirebase(email, password)
        } else {
            showMsg("Preencha todos os dados")
        }
    }

    fun sendDataFirebase(email: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val firebaseUser: FirebaseUser = task.result?.user!!
                    val key = firebaseUser.uid
                    val email = firebaseUser.email.toString()
                    showMsg("Cadastro realizado com sucesso!")
                    callMain(key, email)
                } else {
                    showMsg(task.exception.toString())
                }
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
}