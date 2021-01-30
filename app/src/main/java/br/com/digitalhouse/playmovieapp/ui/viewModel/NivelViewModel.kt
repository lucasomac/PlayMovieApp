package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.domain.Nivel
import br.com.digitalhouse.playmovieapp.domain.nivel.*
import br.com.digitalhouse.playmovieapp.services.*
import kotlinx.coroutines.launch

class NivelViewModel() : ViewModel() {
    val allLevels = MutableLiveData<ArrayList<Level>>()
    var allQuestions = MutableLiveData<ArrayList<Question>>()
    var allQuestionsUser = MutableLiveData<ArrayList<Answered>>()

    fun getAllQuestions() {
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
                    } else {
                        Log.i("XXX", "ERROR.", task.exception)
                    }
                }
        }
    }

    fun getAllQuestionsUser() {
        viewModelScope.launch {
            collectionUsers
                .document("rodrigofelipejr2@gmail.com")
                .collection("answered")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val listAnswered = arrayListOf<Answered>()
                        for (document in task.result!!) {
                            val answered = Answered(
                                document.id as String,
                                document.data["attempts"] as Number,
                                document.data["bonus"] as Boolean,
                                document.data["score"] as Number,
                                document.data["time"] as Number,
                            )
                            listAnswered.add(answered)
                        }
                        Log.i("XXX", listAnswered.toString())

                        allQuestionsUser.value = listAnswered
                        Log.i("XXX", allQuestionsUser.value!!.size.toString())
                    } else {
                        Log.i("XXX", "ERROR.", task.exception)
                    }
                }
        }
    }

    fun getLevels() {
        getAllQuestionsUser()
    }
}