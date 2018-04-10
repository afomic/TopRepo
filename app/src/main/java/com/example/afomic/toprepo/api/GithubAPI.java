package com.example.afomic.toprepo.api;

import android.support.annotation.NonNull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubAPI {
    @GET("repositories?q=android+language:java&sort=stars&order=desc&per_page=10")
    Call<ApiResponse> getRepositories(@Query("page") int page);
}
