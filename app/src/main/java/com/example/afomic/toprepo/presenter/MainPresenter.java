package com.example.afomic.toprepo.presenter;

import com.example.afomic.toprepo.api.ApiResponse;
import com.example.afomic.toprepo.api.RemoteDataSource;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.utils.NetworkUtils;
import com.example.afomic.toprepo.view.MainView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class MainPresenter implements BasePresenter<MainView> {
    private MainView mainView;
    @Inject
    RemoteDataSource dataSource;
    @Inject
    NetworkUtils networkUtils;

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
        dataSource.getRepository(pageNumber, new RemoteDataSource.DataSourceCallback() {
            @Override
            public void onSuccess(List<Repository> repositories) {
                mainView.hideProgressBar();
                mainView.showRepositories(repositories);
                mainView.hideErrorView();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                t.printStackTrace();
                mainView.showMessage(t.getMessage());
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
