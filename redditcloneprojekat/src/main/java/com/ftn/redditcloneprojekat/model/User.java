package com.ftn.redditcloneprojekat.model;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @NotBlank(message = "Username cannot be null")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password cannot be null")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Email cannot be null")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Community> communities = new HashSet<Community>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Reaction> reactions = new HashSet<Reaction>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ReactionOnPost> reactionsOnPost = new HashSet<ReactionOnPost>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<Post>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<Comment>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<Report>();

    public User() {
        super();
    }

    public User(String username, String password, String email, Roles role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Set<Community> getCommunities() {
        return communities;
    }

    public void setCommunities(Set<Community> communities) {
        this.communities = communities;
    }

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Set<ReactionOnPost> getReactionsOnPost() {
        return reactionsOnPost;
    }

    public void setReactionsOnPost(Set<ReactionOnPost> reactionsOnPost) {
        this.reactionsOnPost = reactionsOnPost;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public void addCommunity(Community community) {
        communities.add(community);
        community.setUser(this);
    }

    public void removeCommunity(Community community) {
        communities.remove(community);
        community.setUser(null);
    }

    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
        reaction.setUser(this);
    }

    public void removeReaction(Reaction reaction) {
        reactions.remove(reaction);
        reaction.setUser(null);
    }

    public void addReactionOnPost(ReactionOnPost reactionOnPost) {
        reactionsOnPost.add(reactionOnPost);
        reactionOnPost.setUser(this);
    }

    public void removeReaction(ReactionOnPost reactionOnPost) {
        reactionsOnPost.remove(reactionOnPost);
        reactionOnPost.setUser(null);
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setUser(null);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
        comment.setUser(null);
    }

    public void addReport(Report report) {
        reports.add(report);
        report.setUser(this);
    }

    public void removeReport(Report report) {
        reports.remove(report);
        report.setUser(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User u = (User) o;
        if (u.username == null || username == null) {
            return false;
        }
        return Objects.equals(username, u.username);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(username);
    }

    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
