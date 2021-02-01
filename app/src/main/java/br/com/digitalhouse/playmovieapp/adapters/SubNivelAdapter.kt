package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.SubNivel
import br.com.digitalhouse.playmovieapp.domain.nivel.Question
import com.bumptech.glide.Glide

class SubNivelAdapter(
    val listener: SubNivelListner,
) : RecyclerView.Adapter<SubNivelAdapter.SubNivelViewHolder>() {
    private var listSubNivel = arrayListOf<SubNivel>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): SubNivelAdapter.SubNivelViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_grid_sub_nivel,
            parent,
            false
        )
        return SubNivelViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SubNivelAdapter.SubNivelViewHolder, position: Int) {
        val subNivel = listSubNivel.get(position)
        Glide.with(holder.itemView.context).asBitmap()
            .load("${BASE_URL_IMAGE}/original/${subNivel.image}")
            .placeholder(R.drawable.progress_animation).into(holder.imageViewCapa)

        holder.imageViewOverlay.visibility = if (subNivel.answered) View.VISIBLE else View.INVISIBLE
        holder.imageViewCheck.visibility = if (subNivel.answered) View.VISIBLE else View.INVISIBLE
    }

    override fun getItemCount(): Int = listSubNivel.size

    interface SubNivelListner {
        fun onClickNivel(item: Int)
    }

    inner class SubNivelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var imageViewCapa: ImageView = itemView.findViewById(R.id.imageView_sub_nivel)
        var imageViewOverlay: ImageView = itemView.findViewById(R.id.imageView_sub_nivel_overlay)
        var imageViewCheck: ImageView = itemView.findViewById(R.id.imageView_sub_nivel_check)

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

    fun addSubNivel(questions: ArrayList<SubNivel>) {
        listSubNivel = questions
        notifyDataSetChanged()
    }

    fun getIdSubNivel(index: Int): String = listSubNivel[index].id.toString()
}