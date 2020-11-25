package br.com.digitalhouse.playmovieapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.SubNivel

internal class SubNivelAdapter(
    private val context: Context,
    private val listSubNiveis: List<SubNivel>,
) : BaseAdapter() {

    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageViewSubNivel: ImageView
    private lateinit var imageViewSubNivelOverlay: ImageView
    private lateinit var imageViewSubNivelCheck: ImageView

    override fun getCount(): Int = listSubNiveis.size

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View? {
        var convertView = convertView
        val subNivel = listSubNiveis[position]

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.item_grid_sub_nivel, null)
        }

        imageViewSubNivel = convertView!!.findViewById(R.id.imageView_sub_nivel)
        imageViewSubNivelOverlay = convertView!!.findViewById(R.id.imageView_sub_nivel_overlay)
        imageViewSubNivelCheck = convertView!!.findViewById(R.id.imageView_sub_nivel_check)

        imageViewSubNivel.setImageResource(subNivel.urlImage)

        if (subNivel.isComplete) {
            imageViewSubNivelOverlay.visibility = View.VISIBLE;
            imageViewSubNivelCheck.visibility = View.VISIBLE;
        } else {
            imageViewSubNivelOverlay.visibility = View.GONE;
            imageViewSubNivelCheck.visibility = View.GONE;
        }

//        Glide.with(convertView.context).asBitmap()
//            .load(listSubNiveis[position].urlImage)
//            .into(imageViewSubNivel)

        return convertView
    }
}