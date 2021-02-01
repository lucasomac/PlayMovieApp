package br.com.digitalhouse.playmovieapp.domain.nivel

import java.io.Serializable

data class Question(
    val id: String,
    val level: Number,
    val title: String,
    val image: String,
    val overview: String,
) :
    Serializable {
    override fun toString(): String {
        return "Questions(id='$id', level=$level, title='$title', image='$image', overview='$overview')"
    }
}

data class Level(val level: Int, val totalQuestions: Int, val totalQuestionsAnswered: Int) :
    Serializable {
    override fun toString(): String {
        return "Level(livel=$level, totalQuestions=$totalQuestions, totalQuestionsAnswered=$totalQuestionsAnswered)"
    }
}