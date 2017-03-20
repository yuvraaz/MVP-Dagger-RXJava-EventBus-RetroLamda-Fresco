package com.boommba.mvpexample.app.main.posts.dagger.modules;


import com.boommba.mvpexample.app.model.PostsAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    @Provides
    @Singleton
    PostsAPI providePostsApi() {
        return new PostsAPI();
    }

}
