package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.adapters.NivelResumeAdapter
import br.com.digitalhouse.playmovieapp.databinding.ResumoNivelFragmentBinding
import br.com.digitalhouse.playmovieapp.getNiveis
import br.com.digitalhouse.playmovieapp.ui.viewModel.ResumoNivelViewModel

class ResumoNivelFragment : Fragment(), NivelResumeAdapter.NivelResumeListener {
    private var _binding: ResumoNivelFragmentBinding? = null
    private lateinit var viewModel: ResumoNivelViewModel
    private val binding get() = _binding!!
    val adapter = NivelResumeAdapter(getNiveis(), this)

    companion object {
        fun newInstance() = ResumoNivelFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ResumoNivelFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
//        return inflater.inflate(R.layout.resumo_nivel_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNiveisResume.adapter = adapter
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_resumoNivelFragment_to_filmeDiaFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    override fun onClickNivelResume() {
        TODO("Not yet implemented")
    }
}