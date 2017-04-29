package com.myapp;

import android.app.Application;

public class MyApp extends Application {

    private static MyApp instance;
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        initAppComponent();
        component.inject(this);
    }

    protected void initAppComponent() {
        component = AppComponent.Initializer.init(this);
    }

    public static AppComponent getAppComponent() {
        return instance.getComponent();
    }

    public AppComponent getComponent() {
        return component;
    }
}
