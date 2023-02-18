package com.example.data.sources.remote

import com.example.domain.model.Movie
import com.example.domain.model.response.GenreResponse
import com.example.domain.model.response.MovieResponse
import com.example.domain.model.response.ReviewResponse
import com.example.domain.model.response.VideoResponse
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
interface MovieRemoteDataSourceContract {

    fun getGenreList(): Single<GenreResponse>

    fun getMoviesByGenre(page: Int, id: Int): Single<MovieResponse>

    fun getMovieById(id: Int): Single<Movie>

    fun getReviewByMovieId(id: Int, page: Int): Single<ReviewResponse>

    fun getVideoByMovieId(id: Int): Single<VideoResponse>

}