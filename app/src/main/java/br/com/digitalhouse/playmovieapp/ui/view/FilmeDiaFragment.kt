package br.com.digitalhouse.playmovieapp.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import br.com.digitalhouse.playmovieapp.BASE_URL_IMAGE
import br.com.digitalhouse.playmovieapp.R
import br.com.digitalhouse.playmovieapp.databinding.FragmentFilmeDiaHomeBinding
import br.com.digitalhouse.playmovieapp.domain.movie.Result
import br.com.digitalhouse.playmovieapp.getGenres
import br.com.digitalhouse.playmovieapp.services.repository
import br.com.digitalhouse.playmovieapp.ui.viewModel.FilmeDiaViewModel
import com.bumptech.glide.Glide

class FilmeDiaFragment : Fragment() {
    private var _binding: FragmentFilmeDiaHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var filme: Result
    val viewModel by viewModels<FilmeDiaViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return FilmeDiaViewModel(repository) as T
            }
        }
    }
    companion object {
        fun newInstance() = FilmeDiaFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilmeDiaHomeBinding.inflate(inflater, container, false)
        return binding.root
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
                    .placeholder(R.drawable.progress_animation)
                    .into(binding.filmeImage)
            binding.tvNomeFilme.text = filme.title
            val generos = arrayListOf<String>()
            for (genre in filme.genre_ids) {
                generos.add(getGenres().filter { it.id == genre }[0].name)
            }

            "${filme.release_date.substring(0, 4)} - ${
                "$generos".trim(
                    '[',
                    ']'
                )
            }".also { binding.tvAnoFilme.text = it }
            binding.tvNotaFilme.text = filme.vote_average.toString()

            binding.ivMoviewDetail.setOnClickListener {
                val action =
                    FilmeDiaFragmentDirections.actionFilmeDiaFragmentToDetalhesActivity(filme.id)
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}