package com.antew.rxjava.data.imgur.interceptors;

import com.antew.rxjava.BuildConfig;

import retrofit.RequestInterceptor;

public class ImgurAuthenticationInterceptor implements RequestInterceptor {

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Authorization", "Client-ID " + BuildConfig.IMGUR_CLIENT_ID);
    }
}