package com.example.afomic.toprepo.di;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.afomic.toprepo.BuildConfig;
import com.example.afomic.toprepo.api.GithubAPI;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * GithubUser Service interface factory methods
 */
@Module
public class GithubAPIModule {
    private static final String BASE_URL = "https://api.github.com/search/";
    private static final int DISK_CACHE_SIZE = 10 * 1024 * 1024; // 10MB

    @Singleton
    @Provides
    public static
    GithubAPI provideGithubAPI(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(GithubAPI.class);
    }
    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient( Cache cache, HttpLoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
        httpClientBuilder.cache(cache);
        httpClientBuilder.addInterceptor(loggingInterceptor);
        return httpClientBuilder.build();
    }

    @Singleton
    @Provides
    public static Cache provideCache( Context context) {
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
    @Singleton
    @Provides
    public static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);
        return logging;

    }
}