package com.antonioleiva.mvpexample.app.main.posts.events;


import com.antonioleiva.mvpexample.app.model.pojo.Post;

import java.util.List;

public class NewPostDetailEvent {
    Post posts;


    public NewPostDetailEvent(Post posts) {
        this.posts = posts;
    }


    public Post getPost() {
        return posts;
    }
}
