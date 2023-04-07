package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Comment;
import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.Report;
import com.ftn.redditcloneprojekat.model.User;

import javax.persistence.*;
import java.time.LocalDate;

public class ReportDTO {

    private Integer id;
    private String reportReason;
    private Boolean accepted;
    private UserDTO user;
    private CommentDTO comment;
    private PostDTO post;

    public ReportDTO() {

    }

    public ReportDTO(Report report) {
        id = report.getId();
        reportReason = report.getReportReason();
        accepted = report.getAccepted();
        user = new UserDTO(report.getUser());
        comment = new CommentDTO(report.getComment());
        post = new PostDTO(report.getPost());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportReason() {
        return reportReason;
    }

    public void setReportReason(String reportReason) {
        this.reportReason = reportReason;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CommentDTO getComment() {
        return comment;
    }

    public void setComment(CommentDTO comment) {
        this.comment = comment;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
