package br.com.digitalhouse.playmovieapp.ui.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityCadastroBinding
import com.google.firebase.auth.FirebaseAuth

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        setSupportActionBar(findViewById(R.id.material_toolbar))
        initToolbar()

        init()
    }

    fun setup() {
        binding.buttonCriarConta.setOnClickListener {
            if (checaCampos()) {
                Log.i("TAG", binding.textEmail.text.toString())
                Log.i("TAG", binding.textPassword.text.toString())
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.textEmail.text.toString(),
                    binding.textPasswordConfirm.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result?.user?.email.toString(), ProviderTypes.BASIC)
                    } else {

                        showAlert()
                    }
                }
            } else {
                Toast.makeText(this, "Informe os campos corretamente", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun checaCampos(): Boolean {
        return binding.textEmail.text!!.isNotEmpty()
                && binding.textPassword.text!!.isNotEmpty()
                && binding.textPasswordConfirm.text!!.isNotEmpty()
                && (binding.textPassword.text.toString()
            .equals(binding.textPasswordConfirm.text.toString()))
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

    fun showHome(email: String, provider: ProviderTypes) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
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
}