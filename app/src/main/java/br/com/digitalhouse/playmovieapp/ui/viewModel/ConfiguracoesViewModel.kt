package br.com.digitalhouse.playmovieapp.ui.viewModel

import androidx.lifecycle.ViewModel
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.domain.Configuracao

class ConfiguracoesViewModel() : ViewModel() {
    private val listaConfiguracoes = arrayListOf<Configuracao>(
        Configuracao(
            R.drawable.ic_config_som,
            "Sons e Efeitos",
            "Ligar ou desligar sons das teclas",
        ),
        Configuracao(
            R.drawable.ic_config_editar_perfil,
            "Editar Perfil",
            "Reference site about Lorem Ipsum",
        ),
        Configuracao(
            R.drawable.ic_config_senha,
            "Modificar senha",
            "Reference site about Lorem Ipsum",
        ),
        Configuracao(
            R.drawable.ic_config_idioma,
            "Alterar idioma",
            "Reference site about Lorem Ipsum",
        ),
        Configuracao(
            R.drawable.ic_config_ajuda,
            "Ajuda",
            "Reference site about Lorem Ipsum",
        ),
        Configuracao(
            R.drawable.ic_config_sobre,
            "Sobre o aplicativo",
            "Reference site about Lorem Ipsum",
        ),
    )

    fun getConfiguracoes(): ArrayList<Configuracao> {
        return listaConfiguracoes
    }
}