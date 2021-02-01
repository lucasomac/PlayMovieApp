package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.domain.SubNivel
import br.com.digitalhouse.playmovieapp.domain.nivel.*
import br.com.digitalhouse.playmovieapp.services.*
import kotlinx.coroutines.launch

class SubNivelViewModel() : ViewModel() {
    var allQuestions = MutableLiveData<ArrayList<Question>>()
    var allQuestionsAnswered = MutableLiveData<ArrayList<String>>()
    val allQuestionsFiltered = MutableLiveData<ArrayList<SubNivel>>()

    private lateinit var email: String
    private lateinit var level: Number

    fun start(_level: String, _email: String) {
        level = _level.toInt()
        email = _email;
        getAllQuestions();
    }

    private fun getAllQuestions() {
        viewModelScope.launch {
            collectionQuestions
                .whereEqualTo("level", level)
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
                        Log.i("SubNivelViewModel (1)", allQuestions.value!!.size.toString())
                        Log.i("SubNivelViewModel (1)", allQuestions.value.toString())
                        getAllQuestionsAnswered()
                    } else {
                        Log.i("SubNivelViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }

    private fun getAllQuestionsAnswered() {
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
                        Log.i("SubNivelViewModel (2)", allQuestionsAnswered.value!!.size.toString())
                        Log.i("SubNivelViewModel (2)", allQuestionsAnswered.value.toString())
                        getAllQuestionsFiltered()
                    } else {
                        Log.i("SubNivelViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }

    private fun getAllQuestionsFiltered() {
        val listAllQuestionsFiltered = arrayListOf<SubNivel>()
        allQuestions.value!!.forEach {
            val answered = allQuestionsAnswered.value!!.contains(it.id.trim())

            val subLevel = SubNivel(
                it.id.trim(),
                it.image,
                answered,
            )

            listAllQuestionsFiltered.add(subLevel)
        }
        allQuestionsFiltered.value = listAllQuestionsFiltered
    }
}