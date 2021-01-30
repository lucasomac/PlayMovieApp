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
    var allQuestions = MutableLiveData<ArrayList<Question>>()

    val allLevels = MutableLiveData<ArrayList<Level>>()
    var allQuestionsUser = MutableLiveData<ArrayList<Question>>()

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
//                .document("rodrigofelipejr2@gmail.com")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val listQuestions = arrayListOf<Answered>()
                        for (document in task.result!!) {

                            if (document.id == "rodrigofelipejr2@gmail.com") {
                                val aa = document.get("answered")
                                Log.i("XXXX", aa.toString())

//                                if(document.get("city").equals("karachi") || document.get("city").equals("Karachi")){
//                                    arrayList.add(document.toObject(chatModel::class.java));
//                                }
                            }
//                            val question = Question(
//                                document.id as String,
//                                document.data["level"] as Number,
//                                document.data["title"] as String,
//                                document.data["image"] as String,
//                                document.data["overview"] as String,
//                            )
//                            listQuestions.add(question)
//                            Log.i("XXXX", document.data.toString())
                        }
//                        allQuestions.value = listQuestions
//                        Log.i("XXX", allQuestions.value!!.size.toString())
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