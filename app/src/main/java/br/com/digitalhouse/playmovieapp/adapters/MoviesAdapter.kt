package br.com.digitalhouse.playmovieapp.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.movie.Result
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory


class MoviesAdapter(
    val listener: MovieListener
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    var listaMovies = ArrayList<Result>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_pesquisa,
            parent,
            false
        )
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        val movie = listaMovies[position]
        Log.i("MOVIS", listaMovies.size.toString())
        Log.i("MOVI", movie.toString())

        holder.txtNomeFilmePesquisa.text =
            if (!movie.title.equals(null)) movie.title else R.string.nodata.toString()
        holder.txtAnoFilmePesquisa.text =
            if (!movie.release_date.equals(null)) movie.release_date.substring(
                0,
                4
            ) else R.string.nodate.toString()
        val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
//        if (movie.poster_path.isNotEmpty() && !movie.poster_path.isNullOrEmpty())
        Glide.with(holder.itemView.context)
            .asBitmap()
            .load(BASE_URL_IMAGE + "original" + movie.poster_path)
            .placeholder(R.drawable.progress_animation)
            .transition(withCrossFade(factory))
            .into(holder.imgCapaFilme)
//        else
//            holder.imgCapaFilme.setImageResource(R.drawable.ic_robot)
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

        var imgCapaFilme: ImageView = itemView.findViewById(R.id.imgCapaFilme)
        var imgCapaFilmeBackgroung: ImageView = itemView.findViewById(R.id.imgCapaFilmeBackgroung)
        var txtNomeFilmePesquisa: TextView = itemView.findViewById(R.id.txtNomeFilmePesquisa)
        var txtAnoFilmePesquisa: TextView = itemView.findViewById(R.id.txtAnoFilmePesquisa)


        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onClickMovie(position)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addMovie(list: ArrayList<Result>) {
        listaMovies.addAll(list)
        notifyDataSetChanged()
    }
}