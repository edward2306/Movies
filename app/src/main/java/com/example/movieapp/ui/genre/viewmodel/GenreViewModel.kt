package com.example.movieapp.ui.genre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.response.GenreResponse
import com.example.domain.usecase.GenreListUseCase
import com.example.movieapp.data.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GenreViewModel(
    private val getGenreListUseCase: GenreListUseCase
) : ViewModel() {

    private val genreStateFlow = MutableStateFlow<Resource<GenreResponse>>(Resource.empty())
    var disposable: Disposable? = null

    val genreState: StateFlow<Resource<GenreResponse>>
        get() = genreStateFlow

    fun fetchGenre() {
        genreStateFlow.value = Resource.loading()

        disposable = getGenreListUseCase.execute()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ res ->
                genreStateFlow.value = Resource.success(res)
            }, { throwable ->
                throwable.localizedMessage?.let {
                    genreStateFlow.value = Resource.error(it)
                }
            })
    }

}