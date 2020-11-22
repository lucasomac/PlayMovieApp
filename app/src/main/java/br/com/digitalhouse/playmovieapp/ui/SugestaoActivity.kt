package br.com.digitalhouse.playmovieapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import br.com.digitalhouse.playmovieapp.R

class SugestaoActivity: AppCompatActivity() {
    companion object{
        lateinit var fm : FragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugestao)

        fm = supportFragmentManager
        fm.beginTransaction().add(R.id.fragmentContainerSugestao, FragmentSugestao(), null).commit()
    }
}