package com.ftn.redditcloneprojekat.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flair")
public class Flair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name cannot be null")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "flair",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Post> posts = new HashSet<Post>();

    public Flair() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return id != null && id.equals(f.getId());
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
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
