package com.example.data.mapper

import com.example.data.model.response.RemoteGenreResponse
import com.example.data.model.response.RemoteMovieResponse
import com.example.data.model.response.RemoteReviewsResponse
import com.example.data.model.response.RemoteVideosResponse
import com.example.data.util.orDefault
import com.example.data.util.orFalse
import com.example.domain.model.Genre
import com.example.domain.model.response.MovieResponse
import com.example.domain.model.Movie
import com.example.domain.model.Review
import com.example.domain.model.Video
import com.example.domain.model.response.GenreResponse
import com.example.domain.model.response.ReviewResponse
import com.example.domain.model.response.VideoResponse

class MovieMapper {

    fun mapGenreListFromRemote(remoteGenreFromResponse: RemoteGenreResponse): GenreResponse {
        return GenreResponse(remoteGenreFromResponse.genres?.map { remoteReview ->
                Genre(
                    remoteReview.id.orDefault(),
                    remoteReview.name.orEmpty()
                )
            })
    }

    fun mapMovieGenreFromRemote(remoteMovieGemreFromResponse: RemoteMovieResponse): MovieResponse? {
        return remoteMovieGemreFromResponse.results?.map { remoteMovieGenre ->
            Movie(
                remoteMovieGenre.popularity.orDefault(),
                remoteMovieGenre.vote_count.orDefault(),
                remoteMovieGenre.video.orFalse(),
                remoteMovieGenre.poster_path.orEmpty(),
                remoteMovieGenre.id,
                remoteMovieGenre.adult.orFalse(),
                remoteMovieGenre.backdrop_path.orEmpty(),
                remoteMovieGenre.original_language.orEmpty(),
                remoteMovieGenre.original_title.orEmpty(),
                remoteMovieGenre.title,
                remoteMovieGenre.vote_average.orDefault(),
                remoteMovieGenre.overview.orEmpty(),
                remoteMovieGenre.release_date.orEmpty()
            )
        }?.let {
            MovieResponse(remoteMovieGemreFromResponse.page, remoteMovieGemreFromResponse.total_results,
                remoteMovieGemreFromResponse.total_pages, it
            )
        }
    }

    fun mapFromRemote(remoteMoviesResponse: RemoteMovieResponse): MovieResponse? {
        return remoteMoviesResponse.results?.map { remoteMovie ->
            Movie(
                remoteMovie.popularity.orDefault(),
                remoteMovie.vote_count.orDefault(),
                remoteMovie.video.orFalse(),
                remoteMovie.poster_path.orEmpty(),
                remoteMovie.id,
                remoteMovie.adult.orFalse(),
                remoteMovie.backdrop_path.orEmpty(),
                remoteMovie.original_language.orEmpty(),
                remoteMovie.original_title.orEmpty(),
                remoteMovie.title,
                remoteMovie.vote_average.orDefault(),
                remoteMovie.overview.orEmpty(),
                remoteMovie.release_date.orEmpty()
            )
        }?.let {
            MovieResponse(remoteMoviesResponse.page, remoteMoviesResponse.total_results,
                remoteMoviesResponse.total_pages, it
            )
        }
    }

    fun mapReviewMovieFromRemote(remoteReviewFromResponse: RemoteReviewsResponse): ReviewResponse {
        return ReviewResponse(remoteReviewFromResponse.page, remoteReviewFromResponse.total_results,
            remoteReviewFromResponse.total_pages, remoteReviewFromResponse.results?.map { remoteReview ->
                Review(
                    remoteReview.id.orDefault(),
                    remoteReview.author.orEmpty(),
                    remoteReview.url.orEmpty(),
                    remoteReview.content.orEmpty()
                )
            })
    }

    fun mapVideoMovieFromRemote(remoteVideoFromResponse: RemoteVideosResponse): VideoResponse {
        return VideoResponse( remoteVideoFromResponse.results?.map { remoteVideo ->
                Video(
                    remoteVideo.id.orDefault(),
                    remoteVideo.name.orEmpty(),
                    remoteVideo.key.orEmpty(),
                    remoteVideo.size.orDefault()
                )
            })
    }
}