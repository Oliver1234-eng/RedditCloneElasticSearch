package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.Community;
import com.ftn.redditcloneprojekat.model.Flair;
import com.ftn.redditcloneprojekat.repository.CommunityRepository;
import com.ftn.redditcloneprojekat.repository.FlairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlairService {

    @Autowired
    private FlairRepository flairRepository;

    public Flair findOne(String name) {
        return flairRepository.findById(name).orElseGet(null);
    }

    public List<Flair> findAll() {
        return flairRepository.findAll();
    }

    public Page<Flair> findAll(Pageable page) {
        return flairRepository.findAll(page);
    }

    public Flair save(Flair flair) {
        return flairRepository.save(flair);
    }

    public void remove(String name) {
        flairRepository.deleteById(name);
    }

    public Flair findOneWithPosts(String flairName) {
        return flairRepository.findOneWithPosts(flairName);
    }
}
