package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Genre
import br.com.digitalhouse.playmovieapp.domain.Interesse
import com.google.android.material.switchmaterial.SwitchMaterial

class InteresseAdapter(val listener: InteresseListener) :
    RecyclerView.Adapter<InteresseAdapter.InteresseViewHolder>() {

    var listaInteresses = listOf<Interesse>()

    interface InteresseListener {

        fun onClickInteresse(
            isChecked: Boolean,
            interesseIcon: ImageView,
            interesseDesc: TextView,
            genre: Genre
        )

        fun changeColor(interesseIcon: ImageView, interesseDesc: TextView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InteresseAdapter.InteresseViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_interesse,
            parent,
            false
        )
        return InteresseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: InteresseAdapter.InteresseViewHolder, position: Int) {

        val interesse = listaInteresses[position]
        holder.interesseIcon.setImageResource(interesse.icon)
        holder.interesseDesc.text = interesse.genre.name
        holder.interesseAtivo.setChecked(interesse.ativo)

        if (interesse.ativo) {
            listener.changeColor(holder.interesseIcon, holder.interesseDesc)
        }

        holder.interesseAtivo.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onClickInteresse(
                isChecked,
                holder.interesseIcon,
                holder.interesseDesc,
                interesse.genre
            )
        }

    }

    override fun getItemCount(): Int = listaInteresses.size

    inner class InteresseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var interesseIcon: ImageView = itemView.findViewById(R.id.interesseIcon)
        var interesseDesc: TextView = itemView.findViewById(R.id.interesseDesc)
        var interesseAtivo: SwitchMaterial = itemView.findViewById(R.id.interesseAtivo)
    }

    fun addInteresses(interesses: List<Interesse>) {
        listaInteresses = interesses
        notifyDataSetChanged()
    }
}