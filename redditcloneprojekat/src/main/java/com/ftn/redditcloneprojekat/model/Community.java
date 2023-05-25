package com.ftn.redditcloneprojekat.model;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "community")
public class Community {

    @Id
    @NotBlank(message = "Name cannot be null")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotBlank(message = "Description cannot be null")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message = "Rules cannot be null")
    @Column(name = "rules", nullable = false)
    private String rules;

    @NotBlank(message = "Suspended reason cannot be null")
    @Column(name = "suspendedReason", nullable = false)
    private String suspendedReason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_username")
    private User user;

    @OneToMany(mappedBy = "community", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<Post>();

    public Community() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedReason) {
        this.suspendedReason = suspendedReason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setCommunity(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setCommunity(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Community c = (Community) o;
        return name != null && name.equals(c.getName());
    }

    @Override
    public int hashCode() {
        return 1337;
    }

    @Override
    public String toString() {
        return "Community{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rules='" + rules + '\'' +
                ", suspendedReason='" + suspendedReason + '\'' +
                '}';
    }
}
