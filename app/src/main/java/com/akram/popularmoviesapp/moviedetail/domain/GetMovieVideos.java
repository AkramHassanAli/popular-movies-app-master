

package com.akram.popularmoviesapp.moviedetail.domain;

import com.akram.popularmoviesapp.UseCase;
import com.akram.popularmoviesapp.data.model.Video;
import com.akram.popularmoviesapp.data.source.DataSource;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * This class is an implementation of {@link UseCase} that represents a use case for
 * retrieving the videos for a movie.
 */
public class GetMovieVideos extends UseCase<List<Video>, GetMovieVideos.Params> {

    private final DataSource mRepository;

    public GetMovieVideos(DataSource repository,
                          Scheduler threadExecutor,
                          Scheduler postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        mRepository = repository;
    }

    @Override
    public Observable<List<Video>> buildUseCaseObservable(Params params) {
        return mRepository.getMovieVideos(params.movieId);
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