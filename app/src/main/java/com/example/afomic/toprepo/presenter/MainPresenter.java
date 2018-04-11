package com.example.afomic.toprepo.presenter;

import com.example.afomic.toprepo.api.ApiResponse;
import com.example.afomic.toprepo.api.RemoteDataSource;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.view.MainView;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

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
        mainView.setUpView();
    }

    @Override
    public void dropView() {

    }
    public void loadRepository(){
        mainView.showProgressBar();
        dataSource.getRepository(0, new RemoteDataSource.DataSourceCallback() {
            @Override
            public void onSuccess(List<Repository> repositories) {
                mainView.hideProgressBar();
                mainView.showRepositories(repositories);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                mainView.showMessage(t.getMessage());
                mainView.hideProgressBar();
            }
        });

    }
    public void refreshRepositories(){

    }
    public void repositoryClicked(Repository repository){
        mainView.showRepositoryDetailView(repository);
    }
    public void repositoryDeleted(Repository repository){

    }
    public void loadMoreRepo(){

    }

}
