package com.ftn.redditcloneprojekat.model;

import javax.persistence.*;

@Entity
@Table(name = "reaction_on_post")
public class ReactionOnPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_username")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    public ReactionOnPost() {

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {return post;}

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReactionOnPost r = (ReactionOnPost) o;
        return id != null && id.equals(r.getId());
    }

    @Override
    public int hashCode() {
        return 1337;
    }

    @Override
    public String toString() {
        return "ReactionOnPost{" +
                "id=" + id +
                ", type='" + reactionType + '\'' +
                '}';
    }
}