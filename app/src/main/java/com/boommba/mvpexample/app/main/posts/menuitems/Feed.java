package com.boommba.mvpexample.app.main.posts.menuitems;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boommba.mvpexample.app.R;
import com.boommba.mvpexample.app.main.posts.adapters.PostsListAdapter;
import com.boommba.mvpexample.app.main.posts.dagger.DaggerInjector;
import com.boommba.mvpexample.app.main.posts.decorators.DividerItemDecoration;
import com.boommba.mvpexample.app.main.posts.events.ErrorEvent;
import com.boommba.mvpexample.app.main.posts.events.NewPostsEvent;
import com.boommba.mvpexample.app.main.posts.presenters.PostsPresenter;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Feed extends Fragment {
    @Inject
    PostsPresenter postsPresenter;

    @BindView(R.id.posts_recycler_view)
    RecyclerView postsRecyclerView;

    @BindView(R.id.error_view)
    TextView errorView;

    PostsListAdapter postsListAdapter;
    private View parentView;

    private Unbinder unbinder;


    // I get
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.reside_home, container, false);
        unbinder = ButterKnife.bind(this, parentView);

        DaggerInjector.get().inject(this);
        Fresco.initialize(getActivity());
        initRecyclerView();
        postsPresenter.loadPostsFromAPI();

        return parentView;
    }

/*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.reside_home, container, false);
        ButterKnife.bind(getActivity(), parentView);
        DaggerInjector.get().inject(this);
        Fresco.initialize(getActivity());
        errorView = (TextView) parentView.findViewById(R.id.error_view);
        postsRecyclerView = (RecyclerView) parentView.findViewById(R.id.posts_recycler_view);
        initRecyclerView();


        postsPresenter.loadPostsFromAPI();
        return parentView;
    }
*/

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void initRecyclerView() {
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
