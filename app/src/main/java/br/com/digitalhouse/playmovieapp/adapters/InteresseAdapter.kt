package br.com.digitalhouse.playmovieapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.domain.Interesse

class InteresseAdapter (private val listaInteresses: ArrayList<Interesse>): RecyclerView.Adapter<InteresseAdapter.InteresseViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): InteresseAdapter.InteresseViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: InteresseAdapter.InteresseViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = listaInteresses.size

    inner class InteresseViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }
}