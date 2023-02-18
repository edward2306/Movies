package com.example.movieapp.ui.genre

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.response.GenreResponse
import com.example.movieapp.R
import com.example.movieapp.data.Resource
import com.example.movieapp.databinding.FragmentListGenreBinding
import com.example.movieapp.ui.genre.adapter.GenreAdapter
import com.example.movieapp.ui.genre.viewmodel.GenreViewModel
import com.example.movieapp.util.navigate
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber


class GenreFragment : Fragment(R.layout.fragment_list_genre), GenreAdapter.OnItemClickListener {

    private val genreViewModel: GenreViewModel by sharedViewModel()
    private var binding: FragmentListGenreBinding? = null
    private val _binding get() = binding!!
    private val genreAdapter: GenreAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListGenreBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        genreViewModel.fetchGenre()
        setupObserver()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

        genreViewModel.disposable?.dispose()
    }

    private fun setupObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            genreViewModel.genreState.collect {
                handleGenreDataState(it)
            }
        }
    }

    private fun handleGenreDataState(state: Resource<GenreResponse>) {
        when (state.status) {
            Resource.Status.LOADING -> {
                Log.d("check","testLoading" )
//                binding.srlFragmentMovieList.isRefreshing = true
            }
            Resource.Status.SUCCESS -> {
                Log.d("check","testSukses" )
//                binding.srlFragmentMovieList.isRefreshing = false
                state.data?.let { loadGenre(it) }
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

    private fun loadGenre(movies: GenreResponse) {
        movies.let {
            genreAdapter.clear()
            it.genres?.let { it1 -> genreAdapter.fillList(it1) }
        }
    }

    private fun setupRecyclerView() {
        genreAdapter.setOnGenreClickListener(this)

        binding?.genreRv?.adapter = genreAdapter
    }

//    private fun setupSwipeRefresh() {
//        binding.genreRv.setOnRefres {
//            genreViewModel.fetchGenre()
//        }
//    }


    override fun onItemClick(genreId: Int) {
        navigate(GenreFragmentDirections.actionGenreFragmentToMovieFragment(genreId))
    }

}