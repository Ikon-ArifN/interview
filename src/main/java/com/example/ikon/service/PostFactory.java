package com.example.ikon.service;

import com.example.ikon.entity.Post;

public interface PostFactory {
    Post createPost(Long userId, String title);
}
