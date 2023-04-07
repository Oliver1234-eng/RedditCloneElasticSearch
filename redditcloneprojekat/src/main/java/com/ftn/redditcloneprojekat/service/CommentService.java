package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.Comment;
import com.ftn.redditcloneprojekat.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment findOne(Integer id) {
        return commentRepository.findById(id).orElseGet(null);
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Page<Comment> findAll(Pageable page) {
        return commentRepository.findAll(page);
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public void remove(Integer id) {
        commentRepository.deleteById(id);
    }

    public Comment findOneWithReactions(Integer commentId) {
        return commentRepository.findOneWithReactions(commentId);
    }

    public Comment findOneWithReports(Integer commentId) {
        return commentRepository.findOneWithReports(commentId);
    }
}
