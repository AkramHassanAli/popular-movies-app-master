

package com.akram.popularmoviesapp.util;

import android.database.Cursor;

import com.akram.popularmoviesapp.data.model.Movie;
import com.akram.popularmoviesapp.data.source.local.provider.MovieContract.MovieEntry;

import java.util.ArrayList;
import java.util.List;

public class RepositoryUtils {

    public static List<Movie> toMoviesList(Cursor moviesCursor) {
        ArrayList<Movie> movieList = new ArrayList<>();
        for (moviesCursor.moveToFirst(); !moviesCursor.isAfterLast(); moviesCursor.moveToNext()) {
            Movie movie = new Movie(
                    moviesCursor.getInt(moviesCursor.getColumnIndex(MovieEntry.COLUMN_VOTE_AVERAGE)),
                    moviesCursor.getInt(moviesCursor.getColumnIndex(MovieEntry.COLUMN_ID)),
                    (moviesCursor.getInt(moviesCursor.getColumnIndex(MovieEntry.COLUMN_VIDEO)) != 0),
                    moviesCursor.getFloat(moviesCursor.getColumnIndex(MovieEntry.COLUMN_VOTE_AVERAGE)),
                    moviesCursor.getString(moviesCursor.getColumnIndex(MovieEntry.COLUMN_TITLE)),
                    moviesCursor.getFloat(moviesCursor.getColumnIndex(MovieEntry.COLUMN_POPULARITY)),
                    moviesCursor.getString(moviesCursor.getColumnIndex(MovieEntry.COLUMN_POSTER_PATH)),
                    moviesCursor.getString(moviesCursor.getColumnIndex(MovieEntry.COLUMN_ORIGINAL_LANGUAGE)),
                    moviesCursor.getString(moviesCursor.getColumnIndex(MovieEntry.COLUMN_ORIGINAL_TITLE)),
                    moviesCursor.getString(moviesCursor.getColumnIndex(MovieEntry.COLUMN_BACKDROP_PATH)),
                    (moviesCursor.getInt(moviesCursor.getColumnIndex(MovieEntry.COLUMN_ADULT)) != 0),
                    moviesCursor.getString(moviesCursor.getColumnIndex(MovieEntry.COLUMN_OVERVIEW)),
                    moviesCursor.getString(moviesCursor.getColumnIndex(MovieEntry.COLUMN_RELEASE_DATE)),
                    true
            );
            movieList.add(movie);
        }
        return movieList;
    }

    public static Movie toMovie(Cursor movieCursor) {
        if (movieCursor.getCount() == 0) {
            return new Movie(false);
        }

        movieCursor.moveToFirst();
        return new Movie(
            movieCursor.getInt(movieCursor.getColumnIndex(MovieEntry.COLUMN_VOTE_AVERAGE)),
            movieCursor.getInt(movieCursor.getColumnIndex(MovieEntry.COLUMN_ID)),
            (movieCursor.getInt(movieCursor.getColumnIndex(MovieEntry.COLUMN_VIDEO)) != 0),
            movieCursor.getFloat(movieCursor.getColumnIndex(MovieEntry.COLUMN_VOTE_AVERAGE)),
            movieCursor.getString(movieCursor.getColumnIndex(MovieEntry.COLUMN_TITLE)),
            movieCursor.getFloat(movieCursor.getColumnIndex(MovieEntry.COLUMN_POPULARITY)),
            movieCursor.getString(movieCursor.getColumnIndex(MovieEntry.COLUMN_POSTER_PATH)),
            movieCursor.getString(movieCursor.getColumnIndex(MovieEntry.COLUMN_ORIGINAL_LANGUAGE)),
            movieCursor.getString(movieCursor.getColumnIndex(MovieEntry.COLUMN_ORIGINAL_TITLE)),
            movieCursor.getString(movieCursor.getColumnIndex(MovieEntry.COLUMN_BACKDROP_PATH)),
            (movieCursor.getInt(movieCursor.getColumnIndex(MovieEntry.COLUMN_ADULT)) != 0),
            movieCursor.getString(movieCursor.getColumnIndex(MovieEntry.COLUMN_OVERVIEW)),
            movieCursor.getString(movieCursor.getColumnIndex(MovieEntry.COLUMN_RELEASE_DATE)),
            true
            );
    }
}
