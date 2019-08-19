

package com.akram.popularmoviesapp.movies.domain;

import com.akram.popularmoviesapp.UseCase;
import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.source.DataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving the most popular movies list.
 */
public class GetPopularMovies extends UseCase<List<Movie>, Void> {

    private final DataSource mRepository;

    public GetPopularMovies(DataSource repository,
                            Scheduler threadExecutor,
                            Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    public Observable<List<Movie>> buildUseCaseObservable(Void unused) {
        return mRepository.getPopularMovies();
    }
}