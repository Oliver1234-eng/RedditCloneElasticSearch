package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.Flair;
import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.repository.FlairRepository;
import com.ftn.redditcloneprojekat.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findOne(Integer id) {
        return postRepository.findById(id).orElseGet(null);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findAll(Pageable page) {
        return postRepository.findAll(page);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void remove(Integer id) {
        postRepository.deleteById(id);
    }

    public Post findOneWithReactions(Integer postId) {
        return postRepository.findOneWithReactions(postId);
    }

    public Post findOneWithComments(Integer postId) {
        return postRepository.findOneWithComments(postId);
    }

    public Post findOneWithReports(Integer postId) {
        return postRepository.findOneWithReports(postId);
    }
}
