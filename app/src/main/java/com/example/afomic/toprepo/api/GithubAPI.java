package com.example.afomic.toprepo.api;

import android.support.annotation.NonNull;

import com.example.afomic.toprepo.data.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubAPI {
    @GET("repositories?q=android+language:java&sort=stars&order=desc&per_page="+ Constants.REPOSITORY_PER_PAGE)
    Call<ApiResponse> getRepositories(@Query("page") int page);
}
