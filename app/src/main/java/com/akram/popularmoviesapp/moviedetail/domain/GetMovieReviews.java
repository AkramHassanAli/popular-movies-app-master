

package com.akram.popularmoviesapp.moviedetail.domain;

import com.akram.popularmoviesapp.UseCase;
import com.akram.popularmoviesapp.data.model.Review;
import com.akram.popularmoviesapp.data.source.DataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving the reviews for a movie.
 */
public class GetMovieReviews extends UseCase<List<Review>, GetMovieReviews.Params> {

    private final DataSource mRepository;

    public GetMovieReviews(DataSource repository,
                           Scheduler threadExecutor,
                           Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    public Observable<List<Review>> buildUseCaseObservable(Params params) {
        return mRepository.getMovieReviews(params.movieId);
    }

    public static final class Params {

        private final int movieId;

        private Params(int movieId) {
            this.movieId = movieId;
        }

        public static Params forMovie(int movieId) {
            return new Params(movieId);
        }
    }
}