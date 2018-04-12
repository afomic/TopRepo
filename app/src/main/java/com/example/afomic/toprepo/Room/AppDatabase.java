package com.example.afomic.toprepo.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.afomic.toprepo.model.Repository;

@Database(entities = {Repository.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RepositoryDao repositoryDao();
}