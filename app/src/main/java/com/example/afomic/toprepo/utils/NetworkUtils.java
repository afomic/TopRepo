package com.example.afomic.toprepo.utils;

import android.content.Context;
import android.net.Network;

import javax.inject.Inject;

public class NetworkUtils {
    private Context context;
    @Inject
    public NetworkUtils(Context context){
        this.context=context;
    }
}
