package com.example.afomic.toprepo.di;

import com.example.afomic.toprepo.presenter.RepositoryDetailPresenter;

import dagger.Component;

@Component
public interface RepositoryDetailsPresenterComponent {
    RepositoryDetailPresenter getRepositoryDetailPresenter();
}
