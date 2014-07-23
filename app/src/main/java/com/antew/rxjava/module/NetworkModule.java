package com.antew.rxjava.module;

import android.app.Application;
import android.util.Log;

import com.antew.rxjava.data.imgur.api.ImgurService;
import com.antew.rxjava.data.imgur.interceptors.ImgurAuthenticationInterceptor;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

@Module(
        complete = false,
        library = true
)
public class NetworkModule {
    private static final String TAG = NetworkModule.class.getSimpleName();
    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    public static final String IMGUR_API_URL = "https://api.imgur.com/3";

    @Provides
    @Singleton
    RestAdapter provideImgurAdapter(Endpoint endpoint, Client client) {
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setRequestInterceptor(new ImgurAuthenticationInterceptor())
                .build();
    }

    @Provides @Singleton
    ImgurService provideGalleryService(RestAdapter restAdapter) {
        return restAdapter.create(ImgurService.class);
    }

    @Provides
    @Singleton
    Endpoint provideImgurEndpoint() {
        return Endpoints.newFixedEndpoint(IMGUR_API_URL);
    }

    @Provides
    @Singleton
    Client provideOkHttpClient(OkHttpClient client) {
        return new OkClient(client);
    }

    static OkHttpClient createOkHttpClient(Application app) {
        OkHttpClient client = new OkHttpClient();

        // Install an HTTP cache in the application cache directory.
        try {
            File cacheDir = new File(app.getCacheDir(), "http");
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            client.setCache(cache);
        } catch (IOException e) {
            Log.e(TAG, "Unable to install disk cache.", e);
        }

        return client;
    }

    @Provides @Singleton OkHttpClient provideOkHttpClient(Application app) {
        return createOkHttpClient(app);
    }
}
