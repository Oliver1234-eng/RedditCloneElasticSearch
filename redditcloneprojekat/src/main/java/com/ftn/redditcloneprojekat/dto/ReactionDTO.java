package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Comment;
import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.Reaction;
import com.ftn.redditcloneprojekat.model.User;

import javax.persistence.*;
import java.time.LocalDate;

public class ReactionDTO {

    private Integer id;
    private String type;
    private UserDTO user;
    private PostDTO post;
    private CommentDTO comment;

    public ReactionDTO() {

    }

    public ReactionDTO(Reaction reaction) {
        id = reaction.getId();
        type = reaction.getType();
        user = new UserDTO(reaction.getUser());
        post = new PostDTO(reaction.getPost());
        comment = new CommentDTO(reaction.getComment());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public CommentDTO getComment() {
        return comment;
    }

    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }
}
