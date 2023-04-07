package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Comment;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class CommentDTO {

    private Integer id;
    private String text;
    private Boolean isDeleted;
    private UserDTO user;
    private PostDTO post;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment) {
        id = comment.getId();
        text = comment.getText();
        isDeleted = comment.getDeleted();
        user = new UserDTO(comment.getUser());
        post = new PostDTO(comment.getPost());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}