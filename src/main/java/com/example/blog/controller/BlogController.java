package com.example.blog.controller;

import com.example.blog.model.Post;
import com.example.blog.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Controller
public class BlogController {
    private final BlogService blogService;
    @Value("${blog.name}")
    private String blogName;
    @Value("${upload.directory}")
    private String uploadDir;
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("siteName", blogName);
        model.addAttribute("posts", blogService.getAllPosts());
        return "index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("authors", blogService.getAllAuthors());
        return "create";
    }
    @PostMapping("/create")
    public String submitPost(
            @Valid @ModelAttribute("post") Post post,
            BindingResult result,
            @RequestParam("file") MultipartFile file,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("authors", blogService.getAllAuthors());
            return "create";
        }
        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                File saveFile = new File(uploadDir + fileName);
                saveFile.getParentFile().mkdirs();
                file.transferTo(saveFile);
                post.setImageFileName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        blogService.savePost(post);
        return "redirect:/";
    }
}