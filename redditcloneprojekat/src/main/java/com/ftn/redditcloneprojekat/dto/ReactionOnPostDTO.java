package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.ReactionOnPost;
import com.ftn.redditcloneprojekat.model.ReactionType;

public class ReactionOnPostDTO {

    private Integer id;
    private ReactionType reactionType;
    private UserDTO user;
    private PostDTO post;

    public ReactionOnPostDTO() {

    }

    public ReactionOnPostDTO(ReactionOnPost reactionOnPost) {
        id = reactionOnPost.getId();
        reactionType = reactionOnPost.getReactionType();
        user = new UserDTO(reactionOnPost.getUser());
        post = new PostDTO();
        post.setId(reactionOnPost.getPost().getId());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ReactionType getReactionType() {
        return reactionType;
    }

    public void setReactionType(ReactionType reactionType) {
        this.reactionType = reactionType;
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
