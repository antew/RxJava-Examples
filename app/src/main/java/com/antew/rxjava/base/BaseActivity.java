package com.antew.rxjava.base;

import android.app.Activity;
import android.os.Bundle;

import com.antew.rxjava.RxJavaApp;

import butterknife.ButterKnife;

public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RxJavaApp app = RxJavaApp.get(this);
        app.inject(this);

        ButterKnife.inject(this);
    }
}
