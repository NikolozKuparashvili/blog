package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class BlogRestController {
    private final BlogService blogService;
    public BlogRestController(BlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping("/api/posts")
    public List<Post> getPostsApi() {
        return blogService.getAllPosts();
    }
}