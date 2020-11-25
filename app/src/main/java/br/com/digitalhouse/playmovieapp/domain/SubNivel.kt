package br.com.digitalhouse.playmovieapp.domain

import java.io.Serializable

data class SubNivel(val id: Int, val nivel: Int, val isComplete: Boolean, val urlImage: Int) :
    Serializable

//data class SubNivel(val id: Int, val nivel: Int, val isComplete: Boolean, val urlImage: String) :
//    Serializable
