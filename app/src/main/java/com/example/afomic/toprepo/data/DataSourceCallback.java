package com.example.afomic.toprepo.data;

import java.util.List;

public interface DataSourceCallback<T> {
    void onSuccess(List<T> data);
    void onFailure(Throwable t);
}
