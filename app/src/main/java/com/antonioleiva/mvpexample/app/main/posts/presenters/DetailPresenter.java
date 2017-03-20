package com.antonioleiva.mvpexample.app.main.posts.presenters;

import com.antonioleiva.mvpexample.app.main.posts.events.ErrorEvent;
import com.antonioleiva.mvpexample.app.main.posts.events.NewPostDetailEvent;
import com.antonioleiva.mvpexample.app.model.DetailApi;
import com.antonioleiva.mvpexample.app.model.pojo.Post;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Yuvraaz on 1/31/2017.
 */

public class DetailPresenter {

    DetailApi detailApi;

    @Inject
    public DetailPresenter(DetailApi detailApi) {
        this.detailApi = detailApi;
    }

    public void loadDetail() {
        detailApi.getObservableDetailData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Post>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                EventBus.getDefault().post(new ErrorEvent());
            }

            @Override
            public void onNext(Post post) {

                EventBus.getDefault().post(new NewPostDetailEvent(post));
            }
        });

    }
}
