package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Desenvolvedor

class DesenvolvedorAdapter(
    private val listaDesenvolvedores: ArrayList<Desenvolvedor>,
    val listener: DesenvolvedorListener
): RecyclerView.Adapter<DesenvolvedorAdapter.DesenvolvedorViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DesenvolvedorViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_desenvolvedor,
            parent,
            false
        )
        return DesenvolvedorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DesenvolvedorViewHolder, position: Int) {

        var desenvolvedor = listaDesenvolvedores.get(position)
        holder.desenvolvedorFoto.setImageResource(desenvolvedor.foto)
        holder.desenvolvedorNome.text = desenvolvedor.nome
        holder.desenvolvedorDesc.text = desenvolvedor.descricao
    }

    override fun getItemCount(): Int = listaDesenvolvedores.size

    interface DesenvolvedorListener {
        fun onClickDesenvolvedor(item: Int)
    }

    inner class DesenvolvedorViewHolder(itemView: View):  RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var desenvolvedorFoto: ImageView = itemView.findViewById(R.id.desenvolvedorFoto)
        var desenvolvedorNome: TextView = itemView.findViewById(R.id.desenvolvedorNome)
        var desenvolvedorDesc: TextView = itemView.findViewById(R.id.desenvolvedorDesc)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClickDesenvolvedor(position)
            }
        }
    }
}