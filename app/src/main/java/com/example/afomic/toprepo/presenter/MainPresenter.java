package com.example.afomic.toprepo.presenter;

import com.example.afomic.toprepo.api.RemoteDataSource;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.view.MainView;

import javax.inject.Inject;

public class MainPresenter implements BasePresenter<MainView> {
    private MainView mainView;
    @Inject
    RemoteDataSource dataSource;

    @Inject
    public MainPresenter(){

    }
    @Override
    public void takeView(MainView view) {
        mainView=view;
    }

    @Override
    public void dropView() {

    }
    public void refreshRepositories(){

    }
    public void repositoryClicked(Repository repository){

    }
    public void repositoryDeleted(Repository repository){

    }
    public void loadMoreRepo(int lastPage){

    }

}
