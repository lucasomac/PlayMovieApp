package br.com.digitalhouse.playmovieapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Configuracao
import com.google.android.material.switchmaterial.SwitchMaterial

class ConfiguracaoAdapter(
    private val listaConfiguracoes: ArrayList<Configuracao>
) : RecyclerView.Adapter<ConfiguracaoAdapter.ConfiguracaoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfiguracaoAdapter.ConfiguracaoViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_configuracao,
            parent,
            false
        )
        return ConfiguracaoViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ConfiguracaoAdapter.ConfiguracaoViewHolder,
        position: Int
    ) {

        var configuracao = listaConfiguracoes.get(position)
        holder.configIcone.setImageResource(configuracao.icone)
        holder.configDescricao.text = configuracao.descricao
        holder.configComplemento.text = configuracao.complemento
    }

    override fun getItemCount(): Int = listaConfiguracoes.size

    inner class ConfiguracaoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var configIcone: ImageView = itemView.findViewById(R.id.imageView_config)
        var configDescricao: TextView = itemView.findViewById(R.id.textView_descricao)
        var configComplemento: TextView = itemView.findViewById(R.id.textView_complemento)
    }
}