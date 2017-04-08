package com.framgia.capstone.ui;

/**
 * Created by Duong on 2/6/2017.
 */
public interface BaseView<T> {
    void start();
    void setPresenter(T presenter);
}
