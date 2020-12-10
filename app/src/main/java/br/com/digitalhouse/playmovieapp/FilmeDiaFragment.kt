package br.com.digitalhouse.playmovieapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.digitalhouse.playmovieapp.databinding.FragmentFilmeDiaHomeBinding
import br.com.digitalhouse.playmovieapp.domain.Result
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.FilmeDiaViewModel
import com.bumptech.glide.Glide

class FilmeDiaFragment : Fragment() {
    private var _binding: FragmentFilmeDiaHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var filme: Result
    val viewModel by viewModels<FilmeDiaViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FilmeDiaViewModel(repository) as T
            }
        }
    }

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
//        binding.root.setOnClickListener {
//            findNavController().navigate(R.id.action_filmeDiaFragment_to_resumoNivelFragment)
//        }
        viewModel.searchPopularMovie()
        viewModel.popularMovie.observe(viewLifecycleOwner) {
            filme = it
            if (!filme.backdrop_path.toString().isBlank())
                Glide.with(this).asBitmap()
                    .load(BASE_URL_IMAGE + "original" + filme.backdrop_path)
                    .into(binding.filmeImage)
            else
                Glide.with(this).asBitmap()
                    .load(BASE_URL_IMAGE + "original" + filme.poster_path)
                    .into(binding.filmeImage)
            binding.tvNomeFilme.text = filme.title
            binding.tvAnoFilme.text = filme.release_date.substring(0, 4) + filme.genre_ids
            binding.tvNotaFilme.text = filme.vote_average.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}