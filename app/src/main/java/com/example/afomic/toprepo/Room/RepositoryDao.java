package com.example.afomic.toprepo.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.afomic.toprepo.model.Repository;

import java.util.List;

@Dao
public interface RepositoryDao {
    @Query("SELECT * FROM user")
    List<Repository> getAll();
}
