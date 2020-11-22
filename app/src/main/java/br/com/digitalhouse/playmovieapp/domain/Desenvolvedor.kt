package br.com.digitalhouse.playmovieapp.domain

import java.io.Serializable

data class Desenvolvedor(
    val id: Int,
    var nome: String,
    var descricao: String,
    var idade: Int,
    var miniCv: String,
    var foto: Int
): Serializable