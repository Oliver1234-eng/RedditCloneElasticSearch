package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.Reaction;
import com.ftn.redditcloneprojekat.repository.PostRepository;
import com.ftn.redditcloneprojekat.repository.ReactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;

    public Reaction findOne(Integer id) {
        return reactionRepository.findById(id).orElseGet(null);
    }

    public List<Reaction> findAll() {
        return reactionRepository.findAll();
    }

    public Page<Reaction> findAll(Pageable page) {
        return reactionRepository.findAll(page);
    }

    public Reaction save(Reaction reaction) {
        return reactionRepository.save(reaction);
    }

    public void remove(Integer id) {
        reactionRepository.deleteById(id);
    }
}
