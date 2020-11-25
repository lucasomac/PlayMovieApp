package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Nivel

class NivelResumeAdapter(
    private val listaNiveis: ArrayList<Nivel>,
    val listener: NivelResumeAdapter.NivelResumeListener
) : RecyclerView.Adapter<NivelResumeAdapter.NivelResumeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NivelResumeAdapter.NivelResumeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_nivel,
            parent,
            false
        )
        return NivelResumeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NivelResumeAdapter.NivelResumeViewHolder, position: Int) {
        val nivel = listaNiveis.get(position)
        holder.nivelTextViewNivel.text = "Nível ${nivel.id}"
//        holder.nivelTextViewContagem.text = "${nivel.progresso}/${nivel.qtdQuestions}"
        holder.nivelProgressBar.max = nivel.qtdQuestions
        holder.nivelProgressBar.setProgress(nivel.progresso)
    }

    override fun getItemCount(): Int {
        return listaNiveis.size
    }

    class NivelResumeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nivelProgressBar: ProgressBar = itemView.findViewById(R.id.pgProgressNivel)

        //        var nivelTextViewContagem: TextView = itemView.findViewById(R.id.tvContagemPerguntas)
        var nivelTextViewNivel: TextView = itemView.findViewById(R.id.tvNivel)
    }

    interface NivelResumeListener {
        fun onClickNivelResume()
    }
}