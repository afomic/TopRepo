package com.example.afomic.toprepo.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.afomic.toprepo.BuildConfig;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * GithubUser Service interface factory methods
 */
public class GithubAPIFactory {
    private static final String BASE_URL = "https://api.github.com/search/";
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10MB


    public static GithubAPI makeGithubUserService(@NonNull Context context) {
        return makeGithubUserService(makeOkHttpClient(context));
    }

    private static @NonNull GithubAPI makeGithubUserService(@NonNull OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(GithubAPI.class);
    }

    private static @NonNull OkHttpClient makeOkHttpClient(@NonNull Context context) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.cache(getCache(context));
        httpClientBuilder.addInterceptor(makeLoggingInterceptor());
        return httpClientBuilder.build();
    }

    // region Helper Methods
    private static @Nullable Cache getCache(@NonNull Context context) {
        Cache cache = null;
        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(context.getCacheDir(), "http");
            cache = new Cache(cacheDir, DISK_CACHE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }

    private static HttpLoggingInterceptor makeLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;

    }
}