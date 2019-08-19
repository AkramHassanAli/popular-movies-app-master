

package com.akram.popularmoviesapp.moviedetail;

import com.akram.popularmoviesapp.base.BasePresenter;
import com.akram.popularmoviesapp.base.BaseView;
import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.model.Review;
import com.akram.popularmoviesapp.data.model.Video;

import java.util.List;

public interface MovieDetailContract {

    interface View extends BaseView<Presenter> {

        void showMovie(Movie movie);

        void showReviews(List<Review> reviews);

        void showVideos(List<Video> videos);

        void updateSavedMovie();

        void updateDeletedMovie();

        void showLoading();

        void hideLoading();

        void showMessage(String message);

        void shareMovie(String movie, String video);
    }

    interface Presenter extends BasePresenter {

        void saveOrDeleteMovieAsFavorite();

        void shareMovie();
    }
}