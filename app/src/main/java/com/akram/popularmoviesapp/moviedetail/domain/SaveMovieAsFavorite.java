

package com.akram.popularmoviesapp.moviedetail.domain;

import com.akram.popularmoviesapp.UseCase;
import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.source.DataSource;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * saving a {@link Movie} as favorite.
 */
public class SaveMovieAsFavorite extends UseCase<Boolean, SaveMovieAsFavorite.Params> {

    private final DataSource mRepository;

    public SaveMovieAsFavorite(DataSource repository,
                               Scheduler threadExecutor,
                               Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    public Observable<Boolean> buildUseCaseObservable(Params params) {
        return mRepository.saveMovieAsFavorite(params.movie);
    }

    public static final class Params {

        private final Movie movie;

        private Params(Movie movie) {
            this.movie = movie;
        }

        public static Params forMovie(Movie movie) {
            return new Params(movie);
        }
    }
}