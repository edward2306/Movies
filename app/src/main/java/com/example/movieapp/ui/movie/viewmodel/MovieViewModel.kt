package com.example.movieapp.ui.movie.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.Review
import com.example.domain.model.Video
import com.example.domain.model.response.MovieResponse
import com.example.domain.model.response.ReviewResponse
import com.example.domain.model.response.VideoResponse
import com.example.domain.usecase.MovieReviewUseCase
import com.example.domain.usecase.MovieUseCase
import com.example.domain.usecase.MovieVideoUseCase
import com.example.movieapp.data.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieViewModel(
    private val getMovieUseCase: MovieUseCase,
) : ViewModel() {

    private val movieStateFlow = MutableStateFlow<Resource<MovieResponse>>(Resource.empty())
    var disposable: Disposable? = null

    val movieState: StateFlow<Resource<MovieResponse>>
        get() = movieStateFlow

    fun fetchMovie(id: Int) {
        movieStateFlow.value = Resource.loading()

        disposable = getMovieUseCase.execute(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                movieStateFlow.value = Resource.success(res)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    movieStateFlow.value = Resource.error(it)
                }
            })
    }

}