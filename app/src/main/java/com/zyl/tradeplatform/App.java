package com.zyl.tradeplatform;

import android.app.Application;

/**
 * Created by zhangyinglong on 2017/2/21.
 */
public class App extends Application{
    private static App instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance(){
        return instance;
    }
}
