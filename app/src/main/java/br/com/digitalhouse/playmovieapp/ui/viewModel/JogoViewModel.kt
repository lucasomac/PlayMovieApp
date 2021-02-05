package br.com.digitalhouse.playmovieapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.digitalhouse.playmovieapp.domain.nivel.Question
import br.com.digitalhouse.playmovieapp.domain.nivel.QuestionAnswered
import br.com.digitalhouse.playmovieapp.services.collectionQuestions
import br.com.digitalhouse.playmovieapp.services.collectionUsers
import com.google.firebase.firestore.ktx.getField
import kotlinx.coroutines.launch

class JogoViewModel : ViewModel() {
    var question = MutableLiveData<Question>()

    fun getQuestion(id: String) {
        viewModelScope.launch {
            collectionQuestions.document(id)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("IDd", task.result?.data.toString())
                        val questionReturn = Question(
                            task.result?.id as String,
                            task.result?.data!!["level"] as Number,
                            task.result?.data!!["title"] as String,
                            task.result?.data!!["image"] as String,
                            task.result?.data!!["overview"] as String,
                            task.result?.data!!["optionA"] as String,
                            task.result?.data!!["optionB"] as String,
                            task.result?.data!!["optionC"] as String
                        )
                        question.value = questionReturn
                    } else {
                        Log.i("SubNivelViewModel", "ERROR.", task.exception)
                    }
                }
        }
    }

    fun insereRespondida(id: String, email: String, pontuacao: Int, tentativas: Int) {
        val answered = QuestionAnswered(tentativas, pontuacao)
        collectionUsers.document(email).collection("answered").document(id).set(answered)
    }
}