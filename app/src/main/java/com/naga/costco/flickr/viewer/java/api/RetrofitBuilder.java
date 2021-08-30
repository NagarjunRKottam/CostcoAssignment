package com.naga.costco.flickr.viewer.java.api;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {
    private static final String TAG = RetrofitBuilder.class.getSimpleName();
    private static RetrofitBuilder instance = null;
    private ApiInterface apiInterface;
    private Context context;

    public void init(Context context) {
        this.context = context;
        createRetrofitInstance();
    }

    public static RetrofitBuilder getInstance() {
        if (instance == null) {
            instance = new RetrofitBuilder();
        }
        return instance;
    }

    public void createRetrofitInstance() {
        OkHttpClient okHttpClient = createOkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.flickr.com/services/rest/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }

    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        return httpClient.build();
    }
}
