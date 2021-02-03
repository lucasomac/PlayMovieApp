package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.domain.nivel.Question
import br.com.digitalhouse.playmovieapp.services.collectionQuestions
import com.google.firebase.firestore.ktx.getField
import kotlinx.coroutines.launch

class JogoViewModel : ViewModel() {
    var question = MutableLiveData<Question>()
    fun getQuestion(image: String) {
        viewModelScope.launch {
            collectionQuestions.whereEqualTo("image", image)
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
                        question.value = listQuestions[0]
                    } else {
                        Log.i("SubNivelViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }
}