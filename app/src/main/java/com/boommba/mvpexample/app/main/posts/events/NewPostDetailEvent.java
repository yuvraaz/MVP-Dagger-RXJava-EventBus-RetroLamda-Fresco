package com.boommba.mvpexample.app.main.posts.events;


import com.boommba.mvpexample.app.model.pojo.Post;

public class NewPostDetailEvent {
    Post posts;


    public NewPostDetailEvent(Post posts) {
        this.posts = posts;
    }


    public Post getPost() {
        return posts;
    }
}
