package com.ftn.redditcloneprojekat.model;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Text cannot be null")
    @Column(name = "text", nullable = false)
    private String text;

    @AssertFalse
    @Column(name = "isDeleted", nullable = false)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_username")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reaction> reactions = new HashSet<Reaction>();

    @OneToMany(mappedBy = "comment", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Report> reports = new HashSet<Report>();

    public Comment() {

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

    public Set<Reaction> getReactions() {
        return reactions;
    }

    public void setReactions(Set<Reaction> reactions) {
        this.reactions = reactions;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
        reaction.setComment(this);
    }

    public void removeReaction(Reaction reaction) {
        reactions.remove(reaction);
        reaction.setComment(null);
    }

    public void addReport(Report report) {
        reports.add(report);
        report.setComment(this);
    }

    public void removeReport(Report report) {
        reports.remove(report);
        report.setComment(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment c = (Comment) o;
        return id != null && id.equals(c.getId());
    }

    @Override
    public int hashCode() {
        return 1337;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
