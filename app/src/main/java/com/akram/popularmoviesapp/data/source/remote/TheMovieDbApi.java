

package com.akram.popularmoviesapp.data.source.remote;

import com.akram.popularmoviesapp.data.model.MoviesResponse;
import com.akram.popularmoviesapp.data.model.ReviewsResponse;
import com.akram.popularmoviesapp.data.model.VideosResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TheMovieDbApi {

    @GET("movie/popular")
    Observable<MoviesResponse> getPopularMovies(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Observable<ReviewsResponse> getMovieReviews(@Path("movie_id") int movieId,
                                                @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Observable<VideosResponse> getMovieVideos(@Path("movie_id") int movieId,
                                              @Query("api_key") String apiKey);
}
