package br.com.digitalhouse.playmovieapp.domain

import java.io.Serializable


data class Interesse(val genre: Genre, var ativo: Boolean, var icon: Int) :
    Serializable