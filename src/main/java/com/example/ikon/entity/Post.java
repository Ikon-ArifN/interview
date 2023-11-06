package com.example.ikon.entity;

public class Post {

    private Long userId;
    private String title;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Post(Long userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public Post() {
        // Default constructor
    }
}
