package com.example.popular_presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.popular_data.model.PopularMovie
import com.example.popular_presentation.R
import com.example.popular_presentation.databinding.GalleryRvItemBinding
import com.example.popular_presentation.ui.PopularDetailsFragment.Companion.imageBaseUrl

class PopularPagingAdapter(private val interaction: Interaction? = null) :
    PagingDataAdapter<PopularMovie, PopularPagingAdapter.PagingViewHolder>(diffCallback) {

    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagingViewHolder {
        val binding = GalleryRvItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PagingViewHolder(
            binding,
            interaction
        )
    }

    override fun onBindViewHolder(holder: PagingViewHolder, position: Int) {
        val tile = getItem(position)
        if (tile != null) {
            holder.bind(tile)
        }
    }

    class PagingViewHolder constructor(
        private var binding: GalleryRvItemBinding,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: PopularMovie) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onMovieSelected(movie)
            }

            binding.apply {
                name.text = movie.title
                overview.text = movie.overview

                val imageUrl = "$imageBaseUrl${movie.poster_path}"

                image.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.image_6)
                    error(R.drawable.image_6)
                }

                movie.vote_average.let {
                    favourite.isChecked = it >= 7
                }
            }

        }
    }

    interface Interaction {
        fun onMovieSelected(item: PopularMovie)
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<PopularMovie>() {

            override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
                return oldItem == newItem
            }
        }
    }
}