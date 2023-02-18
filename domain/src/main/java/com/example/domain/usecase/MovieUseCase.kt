package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.model.response.MovieResponse
import com.example.domain.repository.MovieRepository
import io.reactivex.Single

class MovieUseCase(private val movieRepository: MovieRepository) {
    fun execute(id: Int): Single<MovieResponse> {
        return movieRepository.getMovieById(id)
    }
}