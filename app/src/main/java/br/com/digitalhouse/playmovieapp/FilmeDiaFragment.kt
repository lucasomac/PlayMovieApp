package br.com.digitalhouse.playmovieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.digitalhouse.playmovieapp.databinding.FragmentFilmeDiaHomeBinding

class FilmeDiaFragment : Fragment() {
    private var _binding: FragmentFilmeDiaHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FilmeDiaViewModel

    companion object {
        fun newInstance() = FilmeDiaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilmeDiaHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
//        return inflater.inflate(R.layout.fragment_filme_dia_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_filmeDiaFragment_to_resumoNivelFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}