package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.User;
import com.ftn.redditcloneprojekat.repository.PostRepository;
import com.ftn.redditcloneprojekat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findOne(Integer id) {
        return userRepository.findById(id).orElseGet(null);
    }

    public User findOneByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable page) {
        return userRepository.findAll(page);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void remove(Integer id) {
        userRepository.deleteById(id);
    }

//    public User findOneWithReactions(Integer userId) {
//        return userRepository.findOneWithReactions(userId);
//    }
//
//    public User findOneWithComments(Integer userId) {
//        return userRepository.findOneWithComments(userId);
//    }
//
//    public User findOneWithReports(Integer userId) {
//        return userRepository.findOneWithReports(userId);
//    }
//
//    public User findOneWithPosts(Integer userId) {
//        return userRepository.findOneWithPosts(userId);
//    }
//
//    public User findOneWithCommunities(Integer userId) {
//        return userRepository.findOneWithCommunities(userId);
//    }

    public User findOneWithReactions(String userUsername) {
        return userRepository.findOneWithReactions(userUsername);
    }

    public User findOneWithComments(String userUsername) {
        return userRepository.findOneWithComments(userUsername);
    }

    public User findOneWithReports(String userUsername) {
        return userRepository.findOneWithReports(userUsername);
    }

    public User findOneWithPosts(String userUsername) {
        return userRepository.findOneWithPosts(userUsername);
    }

    public User findOneWithCommunities(String userUsername) {
        return userRepository.findOneWithCommunities(userUsername);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    public User findByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    public List<User> findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPasswordAllIgnoringCase(username, password);
    }

    public List<User> pronadjiPoEmailu(String email) {
        return userRepository.pronadjiKorisnikePoEmailu(email);
    }

}
