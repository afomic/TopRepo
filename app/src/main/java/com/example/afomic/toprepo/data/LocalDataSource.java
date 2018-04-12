package com.example.afomic.toprepo.data;

import com.example.afomic.toprepo.Room.AppDatabase;
import com.example.afomic.toprepo.model.Repository;

import java.util.List;

import javax.inject.Inject;

public class LocalDataSource implements DataSource<Repository> {
    @Inject
    AppDatabase appDatabase;
    @Inject
    public LocalDataSource(){

    }
    @Override
    public void getData(int pageNumber, DataSourceCallback<Repository> callback) {
        int start=pageNumber*Constants.REPOSITORY_PER_PAGE;
        List<Repository> repositories =appDatabase.repositoryDao().getRepositories(start);
        callback.onSuccess(repositories);
    }

    @Override
    public void saveData(List<Repository> data) {
        appDatabase.repositoryDao().insertAll(data);

    }
}
