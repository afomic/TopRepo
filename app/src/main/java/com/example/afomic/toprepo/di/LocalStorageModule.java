package com.example.afomic.toprepo.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.afomic.toprepo.Room.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalStorageModule {
    @Singleton
    @Provides
    public AppDatabase provideAppDatabase(Context context){
        return  Room.databaseBuilder(context,
                AppDatabase.class, "repository_db").build();
    }
}
