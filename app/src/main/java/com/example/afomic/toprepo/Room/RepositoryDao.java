package com.example.afomic.toprepo.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.afomic.toprepo.model.Repository;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface RepositoryDao {
    @Query("SELECT * FROM Repository  LIMIT 20, :start")
    List<Repository> getRepositories(int start);

    @Insert(onConflict = REPLACE)
    void insertAll(List<Repository> repositories);
}
