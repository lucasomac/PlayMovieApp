package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Result
import com.bumptech.glide.Glide

class MovieAdapter(
    val listener: MovieListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var listaMovies = ArrayList<Result>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_movie,
            parent,
            false
        )
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieViewHolder, position: Int) {
        val movie = listaMovies[position]
        holder.tvMovieName.text = movie.title
        Glide.with(holder.itemView.context).asBitmap()
            .load(BASE_URL_IMAGE + "original" + movie.poster_path)
            .into(holder.ivMoviePoster)
    }

    override fun getItemCount(): Int = listaMovies.size

    interface MovieListener {
        fun onClickMovie(position: Int)
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        var ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
        var tvMovieName: TextView = itemView.findViewById(R.id.tvMovieName)


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClickMovie(position)
            }
        }
    }

    fun addMovie(list: ArrayList<Result>) {
        listaMovies.addAll(list)
        notifyDataSetChanged()
    }
}