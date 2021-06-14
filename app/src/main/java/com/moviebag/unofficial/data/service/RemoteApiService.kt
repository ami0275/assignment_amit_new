package com.moviebag.unofficial.data.service

import com.moviebag.unofficial.data.model.response.CastResponse
import com.moviebag.unofficial.data.model.response.MovieDetailReponse
import com.moviebag.unofficial.data.model.response.MovieResponse
import com.moviebag.unofficial.data.model.response.ReviewResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RemoteApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/upcoming")
    fun getUpComingMovies(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Observable<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(@Path("movie_id") movieId: Int): Observable<MovieDetailReponse>

    @GET("movie/{movie_id}/similar")
    fun getSimilarMovies(@Path("movie_id") movieId: Int): Observable<MovieResponse>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieId: Int): Observable<CastResponse>

    @GET("movie/{movie_id}/recommendations")
    fun getRecommendationMovies(@Path("movie_id") movieId: Int): Observable<MovieResponse>

    @GET("movie/{movie_id}/reviews")
    fun getMoviesReview(@Path("movie_id") movieId: Int, @Query("page") page: Int): Observable<ReviewResponse>

    @GET("search/movie")
    fun searchMovie(@Query("query") query: String, @Query("page") page: Int): Observable<MovieResponse>
}