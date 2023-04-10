package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.ReactionOnPost;
import com.ftn.redditcloneprojekat.repository.ReactionOnPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionOnPostService {

    @Autowired
    private ReactionOnPostRepository reactionOnPostRepository;

    public ReactionOnPost findOne(Integer id) {
        return reactionOnPostRepository.findById(id).orElseGet(null);
    }

    public List<ReactionOnPost> findAll() {
        return reactionOnPostRepository.findAll();
    }

    public Page<ReactionOnPost> findAll(Pageable page) {
        return reactionOnPostRepository.findAll(page);
    }

    public ReactionOnPost save(ReactionOnPost reactionOnPost) {
        return reactionOnPostRepository.save(reactionOnPost);
    }

    public void remove(Integer id) {
        reactionOnPostRepository.deleteById(id);
    }
}
