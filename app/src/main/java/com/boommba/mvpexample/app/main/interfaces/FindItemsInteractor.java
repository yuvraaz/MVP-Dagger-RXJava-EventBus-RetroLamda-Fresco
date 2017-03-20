package com.boommba.mvpexample.app.main.interfaces;

import java.util.List;

public interface FindItemsInteractor {

    interface OnFinishedListener {
        void onFinished(List<String> items);
    }

    void findItems(OnFinishedListener listener);
}
