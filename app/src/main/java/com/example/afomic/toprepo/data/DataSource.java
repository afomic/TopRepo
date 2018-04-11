package com.example.afomic.toprepo.data;

import java.util.List;

public interface DataSource<T> {
    void getData(int pageNumber,DataSourceCallback<T> callback);
    void saveData(List<T> data);
}
