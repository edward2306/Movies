package com.example.movieapp.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.data.util.BASE_URL_POSTER_SIZE_500
import com.example.domain.model.Movie
import com.example.movieapp.databinding.ItemMovieBinding

class MovieAdapter(var items: List<Movie> = ArrayList()) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(movieId: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position])
    }

    fun setOnMovieClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun fillList(items: List<Movie>) {
        this.items += items
        notifyDataSetChanged()
    }

    fun clear() {
        this.items = emptyList()
    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                Glide.with(this@ViewHolder.itemView)
                    .asBitmap()
                    .load(BASE_URL_POSTER_SIZE_500 + movie.backdrop_path)
                    .into(poster)
                title.text = movie.original_title

                poster.setOnClickListener {
                    onItemClickListener?.onItemClick(movie.id)
                }
            }
        }
    }

    class MovieResponseItemDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}

