package com.example.afomic.toprepo.data;

import android.content.Context;
import android.util.Log;

import com.example.afomic.toprepo.Room.AppDatabase;
import com.example.afomic.toprepo.api.ApiResponse;
import com.example.afomic.toprepo.api.RemoteDataSource;
import com.example.afomic.toprepo.model.Repository;
import com.example.afomic.toprepo.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class DataRepository {
    @Inject
    RemoteDataSource remoteDataSource;
    @Inject
    LocalDataSource localDatasource;
    @Inject
    Context context;
    @Inject
    public DataRepository(){
    }
    public void loadMoreRepository(int page, final RepositoryCallback callback){
        if(NetworkUtils.isInternetConnected(context)){
            remoteDataSource.getRepository(page, new RemoteDataSource.DataSourceCallback() {
                @Override
                public void onSuccess(List<Repository> repositories) {
                    callback.onSuccess(repositories);
                    localDatasource.saveData(repositories);
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    callback.onFailure();
                }
            });
        }else {
            localDatasource.getData(page, new DataSourceCallback<Repository>() {
                @Override
                public void onSuccess(List<Repository> data) {
                    callback.onSuccess(data);
                }

                @Override
                public void onFailure(Throwable t) {
                    callback.onFailure();
                }
            });
        }

    }
}
