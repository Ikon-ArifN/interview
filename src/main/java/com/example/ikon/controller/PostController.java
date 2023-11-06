package com.example.ikon.controller;

import com.example.ikon.entity.Post;
import com.example.ikon.errorResponse.ErrorResponse;
import com.example.ikon.service.PostFactory;
import com.example.ikon.service.impl.PostImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostController {

    private final String url = "https://jsonplaceholder.typicode.com/posts";
    private final RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity responseEntity;

    public final PostFactory postFactory = new PostImpl();

    ErrorResponse<List<Post>> parameterError = new ErrorResponse<>("Error", new ArrayList<Post>());


    @GetMapping("/api/v1/post")
    public ResponseEntity<List<Post>> getPost(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int pageSize
        ) {
        if (page <= 0 || pageSize <= 0) {
            List errorMesssage = new ArrayList<>();
            errorMesssage.add(parameterError.getMessage());
            errorMesssage.add(parameterError.getData());
            return responseEntity.badRequest().body(errorMesssage);
        }

        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("_start", (page - 1) * pageSize)
                    .queryParam("_limit", pageSize);
            Post[] posts = restTemplate.getForObject(builder.toUriString(), Post[].class);

            if (posts != null) {
                List<Post> postList = new ArrayList<>();
                for (Post post : posts) {
                    postList.add(postFactory.createPost(post.getUserId(), post.getTitle()));
                }

                return ResponseEntity.ok(postList);
            }
        } catch (HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new ArrayList<Post>());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ArrayList<Post>());
        }

        return ResponseEntity.noContent().build();
    }

}

