package com.example.movieapp.ui.movie

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.response.GenreResponse
import com.example.domain.model.response.MovieResponse
import com.example.movieapp.R
import com.example.movieapp.data.Resource
import com.example.movieapp.databinding.FragmentListGenreBinding
import com.example.movieapp.databinding.FragmentListMovieBinding
import com.example.movieapp.ui.genre.adapter.GenreAdapter
import com.example.movieapp.ui.genre.viewmodel.GenreViewModel
import com.example.movieapp.ui.movie.adapter.MovieAdapter
import com.example.movieapp.ui.movie.viewmodel.MovieViewModel
import com.example.movieapp.util.navigate
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class MovieFragment: Fragment(R.layout.fragment_list_movie),  MovieAdapter.OnItemClickListener {
    private val movieViewModel: MovieViewModel by sharedViewModel()
    private var binding: FragmentListMovieBinding? = null
    private val _binding get() = binding!!
    private val movieAdapter: MovieAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListMovieBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        arguments?.getInt(MOVIE_GENRE_ID)?.let { movieViewModel.fetchMovie(it) }
        setupObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

        movieViewModel.disposable?.dispose()
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            movieViewModel.movieState.collect {
                handleMovieDataState(it)
            }
        }
    }

    private fun handleMovieDataState(state: Resource<MovieResponse>) {
        when (state.status) {
            Resource.Status.LOADING -> {
                Log.d("check","testLoading" )
//                binding.srlFragmentMovieList.isRefreshing = true
            }
            Resource.Status.SUCCESS -> {
                Log.d("check","testSukses" )
//                binding.srlFragmentMovieList.isRefreshing = false
                state.data?.let { loadMovie(it) }
            }
            Resource.Status.ERROR -> {
                Log.d("check","testError" )
//                binding.srlFragmentMovieList.isRefreshing = false
//                Snackbar.make(binding.genreRv, getString(R.string.genre_fragment_label, state.message), Snackbar.LENGTH_LONG)
//                    .setAnchorId(R.id.bottom_navigation).show()
            }
            Resource.Status.EMPTY -> {
                Timber.d("Empty state.")
            }
        }
    }

    private fun loadMovie(movies: MovieResponse) {
        movies.let {
            movieAdapter.clear()
            it.results.let { its -> movieAdapter.fillList(its) }
        }
    }

    private fun setupRecyclerView() {
        movieAdapter.setOnMovieClickListener(this)

        binding?.movieRv?.adapter = movieAdapter
    }

//    private fun setupSwipeRefresh() {
//        binding.genreRv.setOnRefres {
//            genreViewModel.fetchGenre()
//        }
//    }

    override fun onItemClick(genreId: Int) {
        navigate(MovieFragmentDirections.actionMovieFragmentToDetailFragment(genreId))
    }

    companion object {

        private const val MOVIE_GENRE_ID = "MOVIE_GENRE_ID"

        fun newInstance(id: Int): MovieFragment {
            val fragment = MovieFragment()
            val args = Bundle()
            args.putInt(MOVIE_GENRE_ID, id)
            fragment.arguments = args
            return fragment
        }

    }
}
