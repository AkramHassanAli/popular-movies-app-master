

package com.akram.popularmoviesapp.base;

import io.reactivex.observers.DisposableObserver;

/**
 * Default {@link DisposableObserver} base class to be used whenever you want default
 * error handling.
 */

public class BaseObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        // no-op by default.
    }

    @Override
    public void onComplete() {
        // no-op by default.
    }

    @Override
    public void onError(Throwable exception) {
        // no-op by default.
    }
}