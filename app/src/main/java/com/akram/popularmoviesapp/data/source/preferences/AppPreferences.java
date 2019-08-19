

package com.akram.popularmoviesapp.data.source.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.akram.popularmoviesapp.data.Constants;

public class AppPreferences implements Preferences {

    private static final String PREF_KEY_CURRENT_DISPLAYED_MOVIES = "current_displayed_movies";

    private static volatile AppPreferences INSTANCE;
    private final SharedPreferences mPrefs;

    private AppPreferences(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static AppPreferences getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AppPreferences(context);
        }
        return INSTANCE;
    }

    @Override
    public String getCurrentDisplayedMovies() {
        return mPrefs.getString(PREF_KEY_CURRENT_DISPLAYED_MOVIES, Constants.MOVIES_POPULAR);
    }

    @Override
    public void setCurrentDisplayedMovies(String currentDisplayedMovies) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_DISPLAYED_MOVIES, currentDisplayedMovies).apply();
    }
}
