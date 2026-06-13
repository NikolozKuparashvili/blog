package com.example.blog;

import com.example.blog.model.Post;
import com.example.blog.repository.AuthorRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.BlogService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {
    @Mock
    private PostRepository postRepository;
    @Mock
    private AuthorRepository authorRepository;
    @InjectMocks
    private BlogService blogService;
    @Test
    public void testGetAllPosts() {
        when(postRepository.findAll()).thenReturn(Arrays.asList(new Post(), new Post()));
        List<Post> result = blogService.getAllPosts();
        assertEquals(2, result.size());
    }
    @Test
    public void testSavePost() {
        Post post = new Post();
        blogService.savePost(post);
        verify(postRepository, times(1)).save(post);
    }
    @Test
    public void testInitCreatesAuthor() {
        when(authorRepository.count()).thenReturn(0L);
        blogService.init();
        verify(authorRepository, times(1)).save(any());
    }
}