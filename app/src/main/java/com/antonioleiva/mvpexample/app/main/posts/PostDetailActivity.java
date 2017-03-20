package com.antonioleiva.mvpexample.app.main.posts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.main.posts.dagger.DaggerInjector;
import com.antonioleiva.mvpexample.app.main.posts.events.ErrorEvent;
import com.antonioleiva.mvpexample.app.main.posts.events.NewPostDetailEvent;
import com.antonioleiva.mvpexample.app.main.posts.presenters.DetailPresenter;
import com.antonioleiva.mvpexample.app.model.pojo.Post;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView title;

    @BindView(R.id.tv_body)
    TextView body;

    @BindView(R.id.data_view)
    LinearLayout dataView;

    @BindView(R.id.error_view)
    LinearLayout errorView;


    @Inject
    DetailPresenter detailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);


        ButterKnife.bind(this);
         Fresco.initialize(this);

        detailPresenter.loadDetail();
    }


    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NewPostDetailEvent newPostDetailEvent) {
        hideError();

        updatDate(newPostDetailEvent.getPost());
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ErrorEvent errorEvent) {
        showError();
    }

    private void hideError() {
        dataView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    private void showError() {
        dataView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }


    void updatDate(Post post) {
        title.setText(post.getTitle());
        body.setText(post.getBody());

    }
}
