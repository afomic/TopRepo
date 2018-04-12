package com.example.afomic.toprepo.presenter;

import com.example.afomic.toprepo.api.ApiResponse;
import com.example.afomic.toprepo.api.RemoteDataSource;
import com.example.afomic.toprepo.data.DataRepository;
import com.example.afomic.toprepo.data.RepositoryCallback;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.view.MainView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class MainPresenter implements BasePresenter<MainView> {
    private MainView mainView;
    @Inject
    DataRepository dataRepository;

    @Inject
    public MainPresenter(){

    }
    @Override
    public void takeView(MainView view) {
        mainView=view;
        mainView.setUpView();
    }

    @Override
    public void dropView() {

    }
    public void loadRepository(int pageNumber){
        dataRepository.loadMoreRepository(pageNumber, new RepositoryCallback() {
            @Override
            public void onSuccess(List<Repository> repositories) {
                mainView.hideProgressBar();
                mainView.showRepositories(repositories);
                mainView.hideErrorView();
            }

            @Override
            public void onFailure() {
                mainView.showMessage("Failed to Fetch Data");
                mainView.showErrorView();
                mainView.hideProgressBar();
            }
        });

    }
    public void refreshRepositories(){
        loadRepository(1);
        mainView.showProgressBar();
        mainView.refreshView();
    }
    public void repositoryClicked(Repository repository){
        mainView.showRepositoryDetailView(repository);
    }
    public void repositoryDeleted(Repository repository){

    }

}
