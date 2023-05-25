package com.ftn.redditcloneprojekat.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flair")
public class Flair {

    @Id
    @NotBlank(message = "Name cannot be null")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "flair",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<Post>();

    public Flair() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Flair f = (Flair) o;
        return name != null && name.equals(f.getName());
    }

    @Override
    public int hashCode() {
        return 1337;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setFlair(this);
    }

    public void removePost(Post post) {
        posts.remove(post);
        post.setFlair(null);
    }

    @Override
    public String toString() {
        return "Flair{" +
                ", name='" + name + '\'' +
                '}';
    }
}
