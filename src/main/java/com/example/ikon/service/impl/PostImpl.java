package com.example.ikon.service.impl;

import com.example.ikon.entity.Post;
import com.example.ikon.service.PostFactory;

public class PostImpl implements PostFactory {


    @Override
    public Post createPost(Long userId, String title) {
        return new Post(userId, title);
    }
}
