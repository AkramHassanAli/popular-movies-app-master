

package com.akram.popularmoviesapp.data.source;

import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.model.Review;
import com.akram.popularmoviesapp.data.model.Video;

import java.util.List;

import io.reactivex.Observable;

public interface DataSource {

    Observable<List<Movie>> getPopularMovies();

    Observable<List<Movie>> getTopRatedMovies();

    Observable<List<Movie>> getFavoriteMovies();

    Observable<Movie> getFavoriteMovieById(int movieId);

    Observable<List<Review>> getMovieReviews(int movieId);

    Observable<List<Video>> getMovieVideos(int movieId);

    Observable<Boolean> saveMovieAsFavorite(Movie movie);

    Observable<Boolean> deleteMovieFromFavorites(int movieId);
}