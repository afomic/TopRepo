package com.example.afomic.toprepo.presenter;

public interface BasePresenter<V> {
    void takeView(V view);
    void dropView();
}
