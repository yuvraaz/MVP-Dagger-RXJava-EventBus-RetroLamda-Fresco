package com.antonioleiva.mvpexample.app.model;

import com.antonioleiva.mvpexample.app.model.pojo.Post;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Yuvraaz on 1/31/2017.
 */

public class DetailApi {

    Observable<Post> observable = new Retrofit.Builder().baseUrl("")
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DetailService.class)
            .getDetails()
            .cache();

    public Observable<Post> getObservableDetailData() {
        return observable;
    }

    private interface DetailService {

        @GET("details")
        Observable<Post> getDetails();

    }
}
