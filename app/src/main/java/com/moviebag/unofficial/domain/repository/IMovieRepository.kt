package com.moviebag.unofficial.domain.repository

import com.moviebag.unofficial.data.model.response.CastResponse
import com.moviebag.unofficial.data.model.response.MovieDetailReponse
import com.moviebag.unofficial.data.model.response.MovieResponse
import com.moviebag.unofficial.data.model.response.ReviewResponse
import io.reactivex.Observable

interface IMovieRepository {
    fun getPopularMovies(page: Int): Observable<MovieResponse>
    fun getUpComingMovies(page: Int): Observable<MovieResponse>
    fun getNowPlayingMovies(page: Int): Observable<MovieResponse>
    fun getSimilarMovies(movieId: Int): Observable<MovieResponse>
    fun getRecommendationMovies(movieId: Int): Observable<MovieResponse>
    fun getMovieCredits(movieId: Int): Observable<CastResponse>
    fun getMovieDetail(movieId: Int): Observable<MovieDetailReponse>
    fun getMoviesReview(movieId: Int, page: Int): Observable<ReviewResponse>
    fun searchMovie(query: String, page: Int): Observable<MovieResponse>
}