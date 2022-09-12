package com.example.popular_presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.popular_presentation.PopularViewModel
import com.example.popular_presentation.databinding.FragmentPopularDetailsBinding

class PopularDetailsFragment : Fragment() {

    private var _binding: FragmentPopularDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PopularViewModel by activityViewModels()
    private val args: PopularDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPopularDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = args.movie

        binding.apply {
            tvTitle.text = args.title
            tvRating.text = args.vote_average.toString()
            tvReleaseDate.text = args.release_date
            tvPopularity.text = args.popularity.toString()
            tvVoteCount.text = args.vote_count.toString()
            tvOverview.text = args.overview
            ivBackdrop.load("$imageBaseUrl${args.backdrop_path}")
            ivPoster.load("$imageBaseUrl${args.poster_path}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
        const val imageBaseUrl = "https://image.tmdb.org/t/p/w780"
    }
}