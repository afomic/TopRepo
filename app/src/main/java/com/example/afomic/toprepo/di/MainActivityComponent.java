package com.example.afomic.toprepo.di;

import android.content.Context;

import com.example.afomic.toprepo.Room.AppDatabase;
import com.example.afomic.toprepo.api.GithubAPI;
import com.example.afomic.toprepo.api.RemoteDataSource;
import com.example.afomic.toprepo.presenter.MainPresenter;
import com.example.afomic.toprepo.view.MainActivity;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Singleton
@Component(modules={GithubAPIModule.class,ContextModule.class,LocalStorageModule.class})
public interface MainActivityComponent {
    GithubAPI getGithubAPI();
    Context getContext();
    MainPresenter getMainPresenter();
    OkHttpClient getOkHttpClient();
    Cache getCache();
    HttpLoggingInterceptor getHHttpLoggingInterceptor();
    RemoteDataSource getRemoteDataSource();
    AppDatabase getAppDatabase();
    void inject(MainActivity mainActivity);

}
