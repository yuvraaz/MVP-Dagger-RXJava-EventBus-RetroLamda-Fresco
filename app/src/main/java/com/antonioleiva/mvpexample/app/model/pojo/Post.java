package com.antonioleiva.mvpexample.app.model.pojo;

public class Post {
    private final Integer userId;
    private final Integer id;
    private final String title;
    private final String body;
    private final String price;
    private final String photo;


    public Post(Integer userId, Integer id, String title, String body, String photo, String price) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
        this.photo = photo;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

}
