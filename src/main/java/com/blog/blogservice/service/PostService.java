package com.blog.blogservice.service;

import com.blog.blogservice.model.Post;
import com.blog.blogservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    public Post save(Post post) {
        return postRepository.saveAndFlush(post);
    }

    public Page<Post> findAllOrderedByDatePageable(int page) {
        return postRepository.findAllByOrderByCreateDateDesc(PageRequest.of(subtractPageByOne(page), 5));
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    private int subtractPageByOne(int page){
        return (page < 1) ? 0 : page - 1;
    }
}
