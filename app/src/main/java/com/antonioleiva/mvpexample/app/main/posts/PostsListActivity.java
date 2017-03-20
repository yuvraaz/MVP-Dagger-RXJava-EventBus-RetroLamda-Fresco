package com.antonioleiva.mvpexample.app.main.posts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.antonioleiva.mvpexample.app.R;
import com.antonioleiva.mvpexample.app.main.posts.adapters.PostsListAdapter;
import com.antonioleiva.mvpexample.app.main.posts.dagger.DaggerInjector;
import com.antonioleiva.mvpexample.app.main.posts.decorators.DividerItemDecoration;
import com.antonioleiva.mvpexample.app.main.posts.events.ErrorEvent;
import com.antonioleiva.mvpexample.app.main.posts.events.NewPostsEvent;
import com.antonioleiva.mvpexample.app.main.posts.presenters.PostsPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsListActivity extends AppCompatActivity {
    @Inject
    PostsPresenter postsPresenter;

    @BindView(R.id.posts_recycler_view)
    RecyclerView postsRecyclerView;

    @BindView(R.id.error_view)
    TextView errorView;

    PostsListAdapter postsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        ButterKnife.bind(this);
        DaggerInjector.get().inject(this);
        Fresco.initialize(this);

        initRecyclerView();
        postsPresenter.loadPostsFromAPI();

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

    public void initRecyclerView() {
      postsRecyclerView=(RecyclerView)findViewById(R.id.posts_recycler_view);
        postsRecyclerView.setHasFixedSize(true);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(postsRecyclerView.getContext()));
        postsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        postsRecyclerView.addItemDecoration(new DividerItemDecoration(postsRecyclerView.getContext(),
                DividerItemDecoration.VERTICAL_LIST));
        postsListAdapter = new PostsListAdapter();
        postsRecyclerView.setAdapter(postsListAdapter);
    }


    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NewPostsEvent newPostsEvent) {

        hideError();
        postsListAdapter.addPosts(newPostsEvent.getPosts());
    }

    @SuppressWarnings("unused")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ErrorEvent errorEvent) {
        showError();

        Toast.makeText(this, "has error", Toast.LENGTH_SHORT).show();
    }


    private void hideError() {
        postsRecyclerView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    private void showError() {
        postsRecyclerView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }
}
