package com.antew.rxjava.module;

import android.app.Application;

import com.antew.rxjava.RxJavaApp;
import com.antew.rxjava.RxJavaExampleActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                NetworkModule.class
        },
        injects = {
                RxJavaApp.class,
                RxJavaExampleActivity.class,
                RxJavaExampleActivity.RetainedListFragment.class

        }
)
// Pattern from Jake Wharton's u2020 app
public final class RxJavaModule {
    private final RxJavaApp app;

    public RxJavaModule(RxJavaApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }
}

