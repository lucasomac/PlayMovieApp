package br.com.digitalhouse.playmovieapp.domain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class ResumoAdapter(private val context: Context, private val listResumo: ArrayList<Resumo>) :
    PagerAdapter() {
    override fun getCount(): Int {
        return listResumo.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return `object` == view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val resumo = listResumo.get(position)
        val view = LayoutInflater.from(context)
            .inflate(resumo.layout, container, false)
        container.addView(view, position)
        return view
    }
}