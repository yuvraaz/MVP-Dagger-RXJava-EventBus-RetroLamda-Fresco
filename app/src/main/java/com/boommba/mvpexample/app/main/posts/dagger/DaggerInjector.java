package com.boommba.mvpexample.app.main.posts.dagger;


import com.boommba.mvpexample.app.main.posts.dagger.components.AppComponent;
import com.boommba.mvpexample.app.main.posts.dagger.components.DaggerAppComponent;
import com.boommba.mvpexample.app.main.posts.dagger.modules.AppModule;

public class DaggerInjector {
    private static AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule()).build();
    public static AppComponent get() {
        return appComponent;
    }
}
