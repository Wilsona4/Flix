package com.example.popular_presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.popular_data.model.PopularMovie
import com.example.popular_presentation.PopularViewModel
import com.example.popular_presentation.adapter.PopularPagingAdapter
import com.example.popular_presentation.databinding.FragmentPopularListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularListFragment : Fragment(), PopularPagingAdapter.Interaction {

    private var _binding: FragmentPopularListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PopularViewModel by activityViewModels()

    private lateinit var popularPagingAdapter: PopularPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPopularListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.apply {
            popularPagingAdapter = PopularPagingAdapter(this@PopularListFragment)
            adapter = popularPagingAdapter
        }

        binding.retryButton.setOnClickListener {
            popularPagingAdapter.retry()
        }


        // Collect from the PagingData Flow in the ViewModel, and submit it to the
        // PagingDataAdapter.
        viewLifecycleOwner.lifecycleScope.launch {
            // We repeat on the STARTED lifecycle because an Activity may be PAUSED
            // but still visible on the screen, for example in a multi window app
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.popularMovies.collectLatest { pagingData ->
                        popularPagingAdapter.submitData(pagingData)
                    }
                }

                launch {
                    popularPagingAdapter.loadStateFlow
                        .collectLatest { loadStates ->
                            // Show loading spinner during initial load or refresh.
                            binding.progressBar.isVisible =
                                loadStates.refresh is LoadState.Loading

                            // Show the retry state if initial load or refresh fails.
                            binding.retryButton.isVisible =
                                loadStates.refresh is LoadState.Error && popularPagingAdapter.itemCount == 0

                            binding.tvEmptyList.isVisible =
                                loadStates.refresh is LoadState.Error && popularPagingAdapter.itemCount == 0
                        }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieSelected(item: PopularMovie) {
        val action =
            PopularListFragmentDirections.actionPopularListFragmentToPopularDetailsFragment(item)
        findNavController().navigate(action)
    }
}