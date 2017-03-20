package com.boommba.mvpexample.app.main.posts.events;


import com.boommba.mvpexample.app.model.pojo.Post;

import java.util.List;

public class NewPostsEvent {
    List<Post> posts;


    public NewPostsEvent(List<Post> posts) {
        this.posts = posts;
    }


    public List<Post> getPosts() {
        return posts;
    }
}
