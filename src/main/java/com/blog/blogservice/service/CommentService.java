package com.blog.blogservice.service;

import com.blog.blogservice.model.Comment;
import com.blog.blogservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
