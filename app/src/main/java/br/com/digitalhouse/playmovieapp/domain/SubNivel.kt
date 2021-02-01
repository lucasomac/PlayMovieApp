package br.com.digitalhouse.playmovieapp.domain

import java.io.Serializable

data class SubNivel(val id: String, val image: String, val answered: Boolean) :
    Serializable {
    override fun toString(): String {
        return "SubNivel(id=$id, image='$image', answered=$answered)"
    }
}
