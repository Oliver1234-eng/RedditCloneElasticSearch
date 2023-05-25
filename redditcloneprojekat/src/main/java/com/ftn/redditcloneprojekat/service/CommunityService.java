package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.Comment;
import com.ftn.redditcloneprojekat.model.Community;
import com.ftn.redditcloneprojekat.repository.CommentRepository;
import com.ftn.redditcloneprojekat.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {

    @Autowired
    private CommunityRepository communityRepository;

    public Community findOne(String name) {
        return communityRepository.findById(name).orElseGet(null);
    }

    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    public Page<Community> findAll(Pageable page) {
        return communityRepository.findAll(page);
    }

    public Community save(Community community) {
        return communityRepository.save(community);
    }

    public void remove(String name) {
        communityRepository.deleteById(name);
    }

    public Community findOneWithPosts(String communityName) {
        return communityRepository.findOneWithPosts(communityName);
    }

}
