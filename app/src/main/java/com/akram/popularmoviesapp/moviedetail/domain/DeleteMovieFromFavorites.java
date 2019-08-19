

package com.akram.popularmoviesapp.moviedetail.domain;

import com.akram.popularmoviesapp.UseCase;
import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.source.DataSource;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * deleting a {@link Movie} from favorites.
 */
public class DeleteMovieFromFavorites extends UseCase<Boolean, DeleteMovieFromFavorites.Params> {

    private final DataSource mRepository;

    public DeleteMovieFromFavorites(DataSource repository,
                                    Scheduler threadExecutor,
                                    Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(Params params) {
        return mRepository.deleteMovieFromFavorites(params.movieId);
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