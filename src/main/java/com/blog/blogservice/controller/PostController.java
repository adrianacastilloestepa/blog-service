package com.blog.blogservice.controller;

import com.blog.blogservice.model.Post;
import com.blog.blogservice.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public Page<Post> getAllPostsOrdered(@RequestParam(name = "page") int page) {
        return postService.findAllOrderedByDatePageable(page);
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable(name = "id") Long id) {
        return postService.findForId(id).orElse(null);
    }

    @PostMapping
    public void saveNewPost(@RequestBody @Valid Post post) {
        postService.save(post);
    }
}
