package com.antew.rxjava;

import android.app.Application;
import android.content.Context;

import com.antew.rxjava.module.RxJavaModule;

import dagger.ObjectGraph;

public class RxJavaApp extends Application {
    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();
        buildObjectGraphAndInject();
    }

    public void buildObjectGraphAndInject() {
        objectGraph = ObjectGraph.create(new RxJavaModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object o) {
        objectGraph.inject(o);
    }

    public static RxJavaApp get(Context context) {
        return (RxJavaApp) context.getApplicationContext();
    }
}
