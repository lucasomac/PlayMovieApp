package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.domain.nivel.*
import br.com.digitalhouse.playmovieapp.services.*
import kotlinx.coroutines.launch

class NivelViewModel() : ViewModel() {
    val allLevels = MutableLiveData<ArrayList<Level>>()
    var allQuestions = MutableLiveData<ArrayList<Question>>()
    var allQuestionsAnswered = MutableLiveData<ArrayList<String>>()
    lateinit var email: String

    fun start(_email: String) {
        email = _email
        getAllQuestions()
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            collectionQuestions
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val listQuestions = arrayListOf<Question>()
                        for (document in task.result!!) {
                            val question = Question(
                                document.id as String,
                                document.data["level"] as Number,
                                document.data["title"] as String,
                                document.data["image"] as String,
                                document.data["overview"] as String,
                            )
                            listQuestions.add(question)
                        }
                        allQuestions.value = listQuestions
                        Log.i("NivelViewModel (1)", allQuestions.value!!.size.toString())
                        Log.i("NivelViewModel (1)", allQuestions.value.toString())
                        getAllQuestionsAnswered()
                    } else {
                        Log.i("NivelViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }

    private fun getAllQuestionsAnswered() {
        if (email == null)
            throw IllegalArgumentException("NivelViewModel - email is null")

        viewModelScope.launch {
            collectionUsers
                .document(email)
                .collection("answered")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val listQuestionsAnswered = arrayListOf<String>()
                        for (document in task.result!!) {
                            listQuestionsAnswered.add(document.id.trim())
                        }
                        allQuestionsAnswered.value = listQuestionsAnswered
                        Log.i("NivelViewModel (2)", allQuestionsAnswered.value!!.size.toString())
                        Log.i("NivelViewModel (2)", allQuestionsAnswered.value.toString())
                        getLevels()
                    } else {
                        Log.i("NivelViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }


    private fun getLevels() {
        var levels: List<Number> = allQuestions.value!!
            .distinctBy { it.level }
            .sortedBy { it.level.toInt() }
            .map { it.level }

        var listLevels = arrayListOf<Level>()

        levels.forEach {
            // nível atual
            val currentLevel = it.toInt()

            val allQuestionsFiltred = allQuestions.value!!
                .filter { it.level.toInt() == currentLevel }

            // total de perguntas
            val totalQuestions = allQuestionsFiltred.size

            // total de perguntas respondidas
            val totalQuestionsAnswered = allQuestionsFiltred
                .filter { allQuestionsAnswered.value!!.contains(it.id.trim()) }.size

            val level = Level(currentLevel, totalQuestions, totalQuestionsAnswered)
            listLevels.add(level)
        }

        allLevels.value = listLevels
    }
}