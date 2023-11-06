package com.example.ikon.controller;

import com.example.ikon.entity.Post;
import com.example.ikon.service.PostFactory;
import com.example.ikon.service.impl.PostImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private final String url = "https://jsonplaceholder.typicode.com/posts";
    private final RestTemplate restTemplate = new RestTemplate();

    public final PostFactory post = new PostImpl();

    @GetMapping("/api/v1/post")
    public List<Post> getPost(@RequestParam(defaultValue = "1") int page) {
        Post[] posts = restTemplate.getForObject(url, Post[].class);

        int pageSize = 5;
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, posts.length);

        List<Post> postList = new ArrayList<>();
        for(int i = startIndex; i < endIndex; i++) {
            postList.add(post.createPost(posts[i].getUserId(), posts[i].getTitle()));
        }

        return postList;
    }
}
