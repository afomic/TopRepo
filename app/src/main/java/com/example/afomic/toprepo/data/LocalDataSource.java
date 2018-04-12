package com.example.afomic.toprepo.data;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

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
    public void getData(final int pageNumber, final DataSourceCallback<Repository> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int start=(pageNumber-1)*Constants.REPOSITORY_PER_PAGE;
                final List<Repository> repositories =appDatabase.repositoryDao().getRepositories(start);
                Handler mainHandler = new Handler(Looper.getMainLooper());

                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {
                        if(repositories.size()==0){
                            callback.onFailure(new Throwable("Empty Database"));
                        }else {
                            callback.onSuccess(repositories);
                        }
                    }
                };
                mainHandler.post(myRunnable);
            }
        }).start();


    }

    @Override
    public void saveData(final List<Repository> data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
            appDatabase.repositoryDao().insertAll(data);
            }
         }).start();

    }
}
