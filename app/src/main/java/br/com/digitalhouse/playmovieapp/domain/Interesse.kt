package br.com.digitalhouse.playmovieapp.domain

import java.io.Serializable


data class Interesse(val id: Int, var descricao: String, var ativo: Boolean, var icon: Int): Serializable