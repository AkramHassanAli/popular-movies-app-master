

package com.akram.popularmoviesapp.movies;

import android.support.annotation.NonNull;

import com.akram.popularmoviesapp.base.BaseObserver;
import com.akram.popularmoviesapp.data.Constants;
import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.source.DataSource;
import com.akram.popularmoviesapp.data.source.preferences.Preferences;
import com.akram.popularmoviesapp.movies.domain.GetFavoriteMovies;
import com.akram.popularmoviesapp.movies.domain.GetPopularMovies;
import com.akram.popularmoviesapp.movies.domain.GetTopRatedMovies;
import com.akram.popularmoviesapp.util.schedulers.BaseSchedulerProvider;

import java.util.List;

/**
 * {@link MoviesContract.Presenter} that controls communication between views and models of the presentation
 * layer.
 */
public class MoviesPresenter implements MoviesContract.Presenter {

    private static final int NAV_POPULAR_MOVIES = 0;
    private static final int NAV_TOP_RATED_MOVIES = 1;
    private static final int NAV_FAVORITE_MOVIES = 2;

    @NonNull
    private final DataSource mRepository;

    @NonNull
    private final Preferences mPreferences;

    @NonNull
    private final MoviesContract.View mMoviesView;

    @NonNull
    private final BaseSchedulerProvider mSchedulerProvider;

    private final GetPopularMovies mGetPopularMovies;
    private final GetTopRatedMovies mGetTopRatedMovies;
    private final GetFavoriteMovies mGetFavoriteMovies;

    public MoviesPresenter(@NonNull DataSource repository,
                           @NonNull Preferences preferences,
                           @NonNull MoviesContract.View moviesView,
                           @NonNull BaseSchedulerProvider schedulerProvider) {
        mRepository = repository;
        mPreferences = preferences;
        mMoviesView = moviesView;
        mSchedulerProvider = schedulerProvider;

        mMoviesView.setPresenter(this);

        mGetPopularMovies = new GetPopularMovies(mRepository,
                mSchedulerProvider.computation(),
                mSchedulerProvider.ui());
        mGetTopRatedMovies = new GetTopRatedMovies(mRepository,
                mSchedulerProvider.computation(),
                mSchedulerProvider.ui());
        mGetFavoriteMovies = new GetFavoriteMovies(mRepository,
                mSchedulerProvider.computation(),
                mSchedulerProvider.ui());
    }

    @Override
    public void subscribe() {

        switch (mPreferences.getCurrentDisplayedMovies()) {
            case Constants.MOVIES_POPULAR:
                getPopularMovies();
                break;
            case Constants.MOVIES_TOP_RATED:
                getTopRatedMovies();
                break;
            case Constants.MOVIES_FAVORITE:
                getFavoriteMovies();
            default:
                break;
        }
    }

    @Override
    public void unsubscribe() {
        mGetPopularMovies.dispose();
        mGetTopRatedMovies.dispose();
        mGetFavoriteMovies.dispose();
    }

    @Override
    public void getPopularMovies() {
        mMoviesView.showLoading();
        mGetPopularMovies.execute(new PopularMoviesListObserver(), null);
    }

    @Override
    public void getTopRatedMovies() {
        mMoviesView.showLoading();
        mGetTopRatedMovies.execute(new TopRatedMoviesListObserver(), null);
    }

    @Override
    public void getFavoriteMovies() {
        mMoviesView.showLoading();
        mGetFavoriteMovies.execute(new FavoriteMoviesListObserver(), null);
    }

    private void showMovies(List<Movie> movies, int nav) {
        if (movies.isEmpty()) {
            mMoviesView.showEmptyView(nav);
        } else {
            mMoviesView.showMovies(movies, nav);
        }
    }

    private final class PopularMoviesListObserver extends BaseObserver<List<Movie>> {

        @Override
        public void onNext(List<Movie> movies) {
            showMovies(movies, NAV_POPULAR_MOVIES);
        }

        @Override
        public void onComplete() {
            mMoviesView.hideLoading();
            mPreferences.setCurrentDisplayedMovies(Constants.MOVIES_POPULAR);
        }

        @Override
        public void onError(Throwable e) {

        }
    }

    private final class TopRatedMoviesListObserver extends BaseObserver<List<Movie>> {

        @Override
        public void onNext(List<Movie> movies) {
            showMovies(movies, NAV_TOP_RATED_MOVIES);
        }

        @Override
        public void onComplete() {
            mMoviesView.hideLoading();
            mPreferences.setCurrentDisplayedMovies(Constants.MOVIES_TOP_RATED);
        }

        @Override
        public void onError(Throwable e) {

        }
    }

    private final class FavoriteMoviesListObserver extends BaseObserver<List<Movie>> {

        @Override
        public void onNext(List<Movie> movies) {
            showMovies(movies, NAV_FAVORITE_MOVIES);
        }

        @Override
        public void onComplete() {
            mMoviesView.hideLoading();
            mPreferences.setCurrentDisplayedMovies(Constants.MOVIES_FAVORITE);
        }

        @Override
        public void onError(Throwable e) {

        }
    }
}