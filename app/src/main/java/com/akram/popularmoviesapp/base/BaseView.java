

package com.akram.popularmoviesapp.base;

import com.akram.popularmoviesapp.base.BasePresenter;

public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

}