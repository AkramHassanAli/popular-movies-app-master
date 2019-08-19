

package com.akram.popularmoviesapp.movies;

import com.akram.popularmoviesapp.base.BasePresenter;
import com.akram.popularmoviesapp.base.BaseView;
import com.akram.popularmoviesapp.data.model.Movie;

import java.util.List;

public interface MoviesContract {

    interface View extends BaseView<Presenter> {

        void showMovies(List<Movie> movies, int nav);

        void showEmptyView(int nav);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends BasePresenter {

        void getPopularMovies();

        void getTopRatedMovies();

        void getFavoriteMovies();
    }
}