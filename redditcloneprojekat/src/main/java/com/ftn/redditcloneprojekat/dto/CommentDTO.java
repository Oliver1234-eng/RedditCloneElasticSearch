package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Comment;

public class CommentDTO {

    private Integer id;
    private String text;
    private UserDTO user;
    private PostDTO post;

//    private Integer id;
//    private String text;
//    private Boolean isDeleted;
//    private UserDTO user;
//    private PostDTO post;

    public CommentDTO() {

    }

    public CommentDTO(Comment comment) {
        id = comment.getId();
        text = comment.getText();
        user = new UserDTO(comment.getUser());
        post = new PostDTO(comment.getPost().getId());
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
