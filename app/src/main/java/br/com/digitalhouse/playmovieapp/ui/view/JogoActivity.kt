package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.ActivityJogoBinding
import br.com.digitalhouse.playmovieapp.domain.nivel.Question
import br.com.digitalhouse.playmovieapp.ui.viewModel.JogoViewModel
import com.bumptech.glide.Glide

class JogoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJogoBinding
    private lateinit var image: String
    private lateinit var totalQuestions: String
    private lateinit var id: String
    private val viewModdel: JogoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJogoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle = intent.extras
        image = bundle?.get("image").toString()
        totalQuestions = bundle?.get("totalQuestions").toString()
        id = bundle?.get("id").toString()
        initToolbar()
        viewModdel.question.observeForever {
            Glide.with(this).asBitmap()
                .load("$BASE_URL_IMAGE/original/${it.image}")
                .placeholder(R.drawable.progress_animation).into(binding.capaImage)
        }
        viewModdel.getQuestion(image)
    }

    private fun initToolbar() {
        setSupportActionBar(binding.includeConfigToolbar.materialToolbar)
        setTitle("Pergunta ${id} de ${totalQuestions}")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}