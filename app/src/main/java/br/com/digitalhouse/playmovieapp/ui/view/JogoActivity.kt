package br.com.digitalhouse.playmovieapp.ui.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityJogoBinding
import br.com.digitalhouse.playmovieapp.domain.nivel.Question
import br.com.digitalhouse.playmovieapp.ui.viewModel.JogoViewModel
import com.bumptech.glide.Glide

class JogoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJogoBinding
    private lateinit var totalQuestions: String
    private lateinit var id: String
    private var pontuacao = 100
    private var tentativas = 0
    private val viewModdel: JogoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJogoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val prefs = getSharedPreferences(R.string.prefs_file.toString(), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null).toString()
        val bundle = intent.extras
        totalQuestions = bundle?.get("totalQuestions").toString()
        id = bundle?.get("id").toString()
        val numero = bundle?.get("number")
        initToolbar(numero.toString().toInt() + 1)
        viewModdel.question.observeForever {
            Glide.with(this).asBitmap()
                .load("$BASE_URL_IMAGE/original/${it.image}")
                .placeholder(R.drawable.progress_animation).into(binding.capaImage)
            var lista = arrayListOf(it.optionA,
                it.optionC,
                it.optionB,
                it.title)
            lista.sort()
            binding.btn01.text = lista[0]
            binding.btn02.text = lista[1]
            binding.btn03.text = lista[2]
            binding.btn04.text = lista[3]
        }
        viewModdel.getQuestion(id)
        binding.btn01.setOnClickListener {
            compareButton(it as Button, email)
        }
        binding.btn02.setOnClickListener {
            compareButton(it as Button, email)
        }
        binding.btn03.setOnClickListener {
            compareButton(it as Button, email)
        }
        binding.btn04.setOnClickListener {
            compareButton(it as Button, email)
        }
        binding.btnDica.setOnClickListener {
            tentativas++
        }
    }

    fun compareButton(button: Button, email: String) {
        if (button.text.toString().equals(viewModdel.question.value?.title)) {
            calculaPontos()
            Log.i("ANSW", "" + pontuacao + " " + tentativas)
            viewModdel.insereRespondida(viewModdel.question.value?.id.toString(),
                email,
                pontuacao,
                tentativas)
            Toast.makeText(this, "Aceertouuuuuuu!!", Toast.LENGTH_LONG).show()
            onBackPressed()
        } else {
            tentativas++
            button.isEnabled = false
            button.isClickable = false
            Toast.makeText(this, "Errrrouuuu!!", Toast.LENGTH_LONG).show()
            button.setTextColor(ContextCompat.getColor(button.context, R.color.white))
            button.setBackgroundColor(ContextCompat.getColor(button.context, R.color.gray_acent))
        }
    }

    fun calculaPontos() {
        pontuacao = if (pontuacao > 0) 100 - (tentativas * 30) else 0
    }

    private fun initToolbar(numero: Int) {
        setSupportActionBar(binding.includeConfigToolbar.materialToolbar)
        setTitle("Pergunta ${numero} de ${totalQuestions}")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}