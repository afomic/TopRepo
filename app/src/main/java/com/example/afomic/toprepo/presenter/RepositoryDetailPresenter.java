package com.example.afomic.toprepo.presenter;

import android.content.Intent;

import com.example.afomic.toprepo.data.Constants;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.view.RepositoryDetailView;

import javax.inject.Inject;

public class RepositoryDetailPresenter implements BasePresenter<RepositoryDetailView> {
    private RepositoryDetailView repositoryDetailView;
    @Inject
    public RepositoryDetailPresenter(){

    }
    @Override
    public void takeView(RepositoryDetailView view) {
        repositoryDetailView=view;
        repositoryDetailView.setUpView();

    }

    @Override
    public void dropView() {

    }
    public void handleIntent(Intent intent){
        Repository repository=intent.getParcelableExtra(Constants.EXTRA_REPOSITORY);
        repositoryDetailView.showRepositoryDetails(repository);
    }
}
