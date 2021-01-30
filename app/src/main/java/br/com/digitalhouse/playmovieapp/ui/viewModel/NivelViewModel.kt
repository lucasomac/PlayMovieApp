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
//                        Log.i("XXX (1)", allQuestions.value!!.size.toString())
//                        Log.i("XXX (1)", allQuestions.value.toString())
                        getAllQuestionsUser()
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
                        val listQuestionsAnswered = arrayListOf<String>()
                        for (document in task.result!!) {
                            listQuestionsAnswered.add(document.id)
                        }
                        allQuestionsAnswered.value = listQuestionsAnswered
//                        Log.i("XXX (2)", allQuestionsAnswered.value!!.size.toString())
//                        Log.i("XXX (2)", allQuestionsAnswered.value.toString())
                        getLevels()
                    } else {
                        Log.i("XXX", "ERROR.", task.exception)
                    }
                }
        }
    }

    fun start() {
        getAllQuestions()
    }

    fun getLevels() {
        var levels: List<Number> = allQuestions.value!!
            .distinctBy { it.level }
            .map { it.level }

        Log.i("XXX levels (3)", levels.size.toString())
        Log.i("XXX levels (3)", levels.toString())

        val listLevels = arrayListOf<Level>()

        levels.forEach {
            val currentLevel = it.toInt()

//                allQuestions.value!!
//                    .filter { it.level.toInt() == currentLevel }.size

            val allQuestionsFiltred =  allQuestions.value!!
                .filter { it.level.toInt() == currentLevel }

            val totalQuestions = allQuestionsFiltred.size

            val totalQuestionsAnswered =
                allQuestionsFiltred
                    .filter {
                        Log.i("XXX (3)", "----------------------")
                        Log.i("XXX (3) it.id", it.id)
                        Log.i("XXX (3) allQuestionsAnswered", allQuestionsAnswered.value!!.toString())
                        Log.i("XXX (3) contains", allQuestionsAnswered.value!!.contains(it.id).toString())

                        allQuestionsFiltred.contains(it.id)
                    }.size

            Log.i("XXX (3) forEach", totalQuestionsAnswered.toString())


            val level = Level(currentLevel, totalQuestions, totalQuestionsAnswered)
            listLevels.add(level)
        }

        allLevels.value = listLevels
        Log.i("XXX (3)", allLevels.value!!.size.toString())
        Log.i("XXX (3)", allLevels.value.toString())
    }
}