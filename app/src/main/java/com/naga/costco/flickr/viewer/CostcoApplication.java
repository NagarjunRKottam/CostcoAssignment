package com.naga.costco.flickr.viewer;

import android.app.Application;

import com.naga.costco.flickr.viewer.java.api.RetrofitBuilder;

public class CostcoApplication extends Application {
    private static final String TAG = Application.class.getSimpleName();
    private static CostcoApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        RetrofitBuilder.getInstance().init(this);
        RetrofitBuilder.getInstance().createRetrofitInstance();
    }
}
