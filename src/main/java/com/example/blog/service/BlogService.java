package com.example.blog.service;

import com.example.blog.model.Author;
import com.example.blog.model.Post;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.PostRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Profile("dev")
public class BlogService {
    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;
    public BlogService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }
    @PostConstruct
    public void init() {
        if (authorRepository.count() == 0) {
            authorRepository.save(new Author("Joe Rogan"));
        }
    }
    @Cacheable("postsCache")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    @CacheEvict(value = "postsCache", allEntries = true)
    public void savePost(Post post) {
        postRepository.save(post);
    }
    @Scheduled(fixedRate = 60000)
    public void logServerStatus() {
        System.out.println("Server is running. Total posts: " + postRepository.count());
    }
}