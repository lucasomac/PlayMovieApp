package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor
import br.com.digitalhouse.playmovieapp.domain.Nivel

class NivelAdapter(
    val listener: NivelListener
) : RecyclerView.Adapter<NivelAdapter.NivelViewHolder>() {

    private var listaNiveis = arrayListOf<Nivel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NivelAdapter.NivelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_nivel,
            parent,
            false
        )
        return NivelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NivelAdapter.NivelViewHolder, position: Int) {
        val nivel = listaNiveis.get(position)
        holder.nivelTextViewNivel.text = "Nídel ${nivel.id}"
        holder.nivelTextViewContagem.text = "${nivel.progresso}/${nivel.qtdQuestions}"
        holder.nivelProgressBar.max = nivel.qtdQuestions
        holder.nivelProgressBar.setProgress(nivel.progresso)
//        if (listaNiveis.get(position - 1).id != 1) {
//            if (listaNiveis.get(position - 1).progresso != listaNiveis.get(position - 1).qtdQuestions){
//
//            }
//        }
    }

    override fun getItemCount(): Int = listaNiveis.size

    interface NivelListener {
        fun onClickNivel(item: Int)
    }


    inner class NivelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var nivelProgressBar: ProgressBar = itemView.findViewById(R.id.pgProgressNivel)
        var nivelTextViewContagem: TextView = itemView.findViewById(R.id.tvContagemPerguntas)
        var nivelTextViewNivel: TextView = itemView.findViewById(R.id.tvNivel)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClickNivel(position)
            }
        }
    }

    fun addNiveis (niveis: ArrayList<Nivel>) {
        listaNiveis = niveis
        notifyDataSetChanged()
    }

}