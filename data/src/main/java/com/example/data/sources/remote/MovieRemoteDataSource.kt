package com.example.data.sources.remote
//
//import com.example.data.scheduler.BaseSchedulerProvider
//import com.example.data.service.MovieService
//import com.example.domain.model.response.GenreResponse
//import com.example.domain.model.response.MovieResponse
//import com.example.domain.model.response.ReviewResponse
//import com.example.domain.model.response.VideoResponse
//import com.example.domain.model.Movie
//import com.example.domain.repository.MovieRepository
//import io.reactivex.Single
//import javax.inject.Inject
//
//class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService, private val schedulerProvider: BaseSchedulerProvider
//) : MovieRepository {
//
//    override fun getGenreList(): List<GenreResponse> {
//        return movieService.getGenreList()
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//    }
//
//    override fun getMoviesByGenre(page: Int, id: Int): Single<MovieResponse> {
//        return movieService.getMoviesByGenre(page, id)
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//    }
//
//    override fun getMovieById(id: Int): Single<Movie> {
//        return movieService.getMovieById(id)
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//    }
//
//    override fun getReviewByMovieId(id: Int, page: Int): Single<ReviewResponse> {
//        return movieService.getReviewByMovieId(id, page)
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//    }
//
//    override fun getVideoByMovieId(id: Int): Single<VideoResponse> {
//        return movieService.getVideoByMovieId(id)
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//    }
//
//}