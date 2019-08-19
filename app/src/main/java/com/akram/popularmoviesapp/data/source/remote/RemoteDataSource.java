

package com.akram.popularmoviesapp.data.source.remote;

import android.support.annotation.Nullable;

import com.akram.popularmoviesapp.BuildConfig;
import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.model.MoviesResponse;
import com.akram.popularmoviesapp.data.model.Review;
import com.akram.popularmoviesapp.data.model.ReviewsResponse;
import com.akram.popularmoviesapp.data.model.Video;
import com.akram.popularmoviesapp.data.model.VideosResponse;
import com.akram.popularmoviesapp.data.source.DataSource;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Concrete implementation of a remote data source that adds a latency simulating network.
 */
public class RemoteDataSource implements DataSource {

    private static final String THE_MOVIE_DB_BASE_URL = "https://api.themoviedb.org/3/";

    @Nullable
    private static RemoteDataSource INSTANCE = null;
    private TheMovieDbApi mTheMovieDbApi;

    // Prevent direct instantiation.
    private RemoteDataSource() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(THE_MOVIE_DB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mTheMovieDbApi = retrofit.create(TheMovieDbApi.class);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @return the {@link RemoteDataSource} instance
     */
    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance()} to create a new instance next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<List<Movie>> getPopularMovies() {
        return mTheMovieDbApi.getPopularMovies(BuildConfig.TMDB_API_KEY)
                .map(MoviesResponse::getMovies);
    }

    @Override
    public Observable<List<Movie>> getTopRatedMovies() {
        return mTheMovieDbApi.getTopRatedMovies(BuildConfig.TMDB_API_KEY)
                .map(MoviesResponse::getMovies);
    }

    @Override
    public Observable<List<Movie>> getFavoriteMovies() {
        // Not used yet
        return null;
    }

    @Override
    public Observable<Movie> getFavoriteMovieById(int movieId) {
        // Not used yet
        return null;
    }

    @Override
    public Observable<List<Review>> getMovieReviews(int movieId) {
        return mTheMovieDbApi.getMovieReviews(movieId, BuildConfig.TMDB_API_KEY)
                .map(ReviewsResponse::getReviews);
    }

    @Override
    public Observable<List<Video>> getMovieVideos(int movieId) {
        return mTheMovieDbApi.getMovieVideos(movieId, BuildConfig.TMDB_API_KEY)
                .map(VideosResponse::getVideos);
    }

    @Override
    public Observable<Boolean> saveMovieAsFavorite(Movie movie) {
        // Not used yet
        return null;
    }

    @Override
    public Observable<Boolean> deleteMovieFromFavorites(int movieId) {
        // Not used yet
        return null;
    }
}