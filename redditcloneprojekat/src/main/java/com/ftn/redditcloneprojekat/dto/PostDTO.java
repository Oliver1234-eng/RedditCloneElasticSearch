package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Community;
import com.ftn.redditcloneprojekat.model.Flair;
import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.User;

import javax.persistence.*;
import java.time.LocalDate;

public class PostDTO {

    private Integer id;
    private String title;
    private String text;
    private UserDTO user;
    private FlairDTO flair;
    private CommunityDTO community;

//    private Integer id;
//    private String title;
//    private String text;
//    private String imagePath;
//    private UserDTO user;
//    private FlairDTO flair;
//    private CommunityDTO community;

    public PostDTO() {

    }

    public PostDTO(Post post) {
        id = post.getId();
        title = post.getTitle();
        text = post.getText();
        user = new UserDTO(post.getUser());
        flair = new FlairDTO(post.getFlair().getName());
        community = new CommunityDTO(post.getCommunity().getName());
    }

    public PostDTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public FlairDTO getFlair() {
        return flair;
    }

    public void setFlair(FlairDTO flair) {
        this.flair = flair;
    }

    public CommunityDTO getCommunity() {
        return community;
    }

    public void setCommunity(CommunityDTO community) {
        this.community = community;
    }

    //    public FlairDTO getFlair() {
//        return flair;
//    }
//
//    public void setFlair(FlairDTO flair) {
//        this.flair = flair;
//    }
//
//    public CommunityDTO getCommunity() {
//        return community;
//    }
//
//    public void setCommunity(CommunityDTO community) {
//        this.community = community;
//    }
}
