package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.serie.Result
import com.bumptech.glide.Glide

class SeriesAdapter(
    val listener: SerieListener
) : RecyclerView.Adapter<SeriesAdapter.SerieViewHolder>() {
    var listaSeries = ArrayList<Result>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SerieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pesquisa,
            parent,
            false
        )
        return SerieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SeriesAdapter.SerieViewHolder, position: Int) {
        val serie = listaSeries[position]
        holder.txtNomeFilmePesquisa.text = serie.name
        holder.txtAnoFilmePesquisa.text = serie.first_air_date.substring(0, 4)
        Glide.with(holder.itemView.context).asBitmap()
            .load(BASE_URL_IMAGE + "original" + serie.poster_path)
            .into(holder.imgCapaFilme)
//        Glide.with(holder.itemView.context).asBitmap()
//            .load(BASE_URL_IMAGE + "original" + serie.backdrop_path)
//            .into(holder.imgCapaFilmeBackgroung)
    }

    override fun getItemCount(): Int = listaSeries.size

    interface SerieListener {
        fun onClickSerie(position: Int)
    }


    inner class SerieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        var imgCapaFilmeBackgroung: ImageView = itemView.findViewById(R.id.imgCapaFilmeBackgroung)
        var imgCapaFilme: ImageView = itemView.findViewById(R.id.imgCapaFilme)
        var txtNomeFilmePesquisa: TextView = itemView.findViewById(R.id.txtNomeFilmePesquisa)
        var txtAnoFilmePesquisa: TextView = itemView.findViewById(R.id.txtAnoFilmePesquisa)


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClickSerie(position)
            }
        }
    }

    fun addSerie(list: ArrayList<Result>) {
        listaSeries.addAll(list)
        notifyDataSetChanged()
    }
}