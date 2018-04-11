package com.example.afomic.toprepo.view;

import com.example.afomic.toprepo.model.Repository;

import java.util.List;

public interface MainView extends BaseView {
    void showRepositories(List<Repository> repositories);
    void removeRepository(Repository repository);
    void showRepositoryDetailView(Repository repository);
    void showProgressBar();
    void hideProgressBar();
    void refreshView();
    void showErrorView();
    void hideErrorView();

}
