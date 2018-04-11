package com.example.afomic.toprepo.api;

import com.example.afomic.toprepo.model.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSource {
    public interface DataSourceCallback{
        void onSuccess(List<Repository> repositories);
        void onFailure(Call<ApiResponse> call, Throwable t);

    }
    @Inject
    GithubAPI githubAPI;
    @Inject
    public RemoteDataSource(){
    }
    public void getRepository(int page, final DataSourceCallback dataSourceCallback){
        githubAPI.getRepositories(page).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Repository> repositories=new ArrayList<>();
                if(response.isSuccessful()){
                    ApiResponse apiResponse=response.body();
                    for(Item item: apiResponse.getItems()){
                        Repository repository=new Repository(item);
                        repositories.add(repository);
                    }
                    dataSourceCallback.onSuccess(repositories);
                }else {
                    dataSourceCallback.onFailure(call,new Throwable("Api call not Successful"));
                    if(!call.isExecuted()){
                        call.enqueue(this);
                    };
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                dataSourceCallback.onFailure(call,t);
            }
        });

    }

}
