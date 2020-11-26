package br.com.digitalhouse.playmovieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.SubNivel
import kotlinx.android.synthetic.main.item_grid_sub_nivel.view.*

internal class SubNivelAdapter(
    private val listSubNiveis: List<SubNivel>,
    val onClickListener: SubNivelListener
) : RecyclerView.Adapter<SubNivelAdapter.SubNivelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SubNivelViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_grid_sub_nivel,
            parent,
            false
        )
        return SubNivelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubNivelViewHolder, position: Int) {
        var subNivel = listSubNiveis.get(position)

        holder.imageViewSubNivel.setImageResource(subNivel.urlImage)

        if (subNivel.isComplete) {
            holder.imageViewSubNivelOverlay.visibility = View.VISIBLE;
            holder.imageViewSubNivelCheck.visibility = View.VISIBLE;
        } else {
            holder.imageViewSubNivelOverlay.visibility = View.GONE;
            holder.imageViewSubNivelCheck.visibility = View.GONE;
        }
    }

    override fun getItemCount(): Int = listSubNiveis.size

    interface SubNivelListener {
        fun onClickListener(item: Int)
    }

    inner class SubNivelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        var imageViewSubNivel: ImageView = itemView.findViewById(R.id.imageView_sub_nivel)
        var imageViewSubNivelOverlay: ImageView =
            itemView.findViewById(R.id.imageView_sub_nivel_overlay)
        var imageViewSubNivelCheck: ImageView =
            itemView.findViewById(R.id.imageView_sub_nivel_check)

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onClickListener.onClickListener(position)
            }
        }
    }
}