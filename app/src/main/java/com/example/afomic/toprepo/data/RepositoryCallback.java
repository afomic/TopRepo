package com.example.afomic.toprepo.data;

import com.example.afomic.toprepo.model.Repository;

import java.util.List;

public interface RepositoryCallback {
    void onSuccess(List<Repository> repositories);
    void onFailure();
}
