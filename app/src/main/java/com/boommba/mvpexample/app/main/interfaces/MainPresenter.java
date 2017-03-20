package com.boommba.mvpexample.app.main.interfaces;

public interface MainPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
