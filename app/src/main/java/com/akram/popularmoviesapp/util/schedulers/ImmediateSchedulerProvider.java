

package com.akram.popularmoviesapp.util.schedulers;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Implementation of the {@link BaseSchedulerProvider} making all {@link Scheduler}s trampoline.
 */
public class ImmediateSchedulerProvider implements BaseSchedulerProvider {

    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}