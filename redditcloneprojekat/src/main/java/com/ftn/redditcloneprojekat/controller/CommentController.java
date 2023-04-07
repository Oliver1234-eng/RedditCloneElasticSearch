package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.*;
import com.ftn.redditcloneprojekat.model.*;
import com.ftn.redditcloneprojekat.service.CommentService;
import com.ftn.redditcloneprojekat.service.PostService;
import com.ftn.redditcloneprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments() {

        List<Comment> comments = commentService.findAll();

        List<CommentDTO> commentsDTO = new ArrayList<>();
        for (Comment c : comments) {
            commentsDTO.add(new CommentDTO(c));
        }

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable Integer id) {

        Comment comment = commentService.findOne(id);

        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO) {

        if (commentDTO.getUser() == null || commentDTO.getPost() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findOneWithComments(commentDTO.getUser().getUsername());
        Post post = postService.findOneWithComments(commentDTO.getPost().getId());

        if (user == null || post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        comment.setDeleted(commentDTO.getDeleted());
        comment.setUser(user);
        comment.setPost(post);
        post.addComment(comment);
        user.addComment(comment);

        comment = commentService.save(comment);
        return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO commentDTO) {

        Comment comment = commentService.findOne(commentDTO.getId());
        if (comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        comment.setText(commentDTO.getText());
        comment.setDeleted(commentDTO.getDeleted());

        comment = commentService.save(comment);
        return new ResponseEntity<>(new CommentDTO(comment), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {

        Comment comment = commentService.findOne(id);

        if (comment != null) {
            commentService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{commentId}/reactions")
    public ResponseEntity<List<ReactionDTO>> getCommentReactions(@PathVariable Integer commentId) {

        Comment comment = commentService.findOneWithReactions(commentId);

        if (comment == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Reaction> reactions = comment.getReactions();

        List<ReactionDTO> reactionsDTO = new ArrayList<>();

        for (Reaction r : reactions) {
            ReactionDTO reactionDTO = new ReactionDTO();
            reactionDTO.setId(r.getId());
            reactionDTO.setType(r.getType());
            reactionDTO.setUser(new UserDTO(r.getUser()));
            reactionDTO.setPost(new PostDTO(r.getPost()));
            reactionDTO.setComment(new CommentDTO(comment));

            reactionsDTO.add(reactionDTO);
        }

        return new ResponseEntity<>(reactionsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{commentId}/reports")
    public ResponseEntity<List<ReportDTO>> getCommentReports(@PathVariable Integer commentId) {

        Comment comment = commentService.findOneWithReports(commentId);

        Set<Report> reports = comment.getReports();
        List<ReportDTO> reportsDTO = new ArrayList<>();

        for (Report r : reports) {
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(r.getId());
            reportDTO.setReportReason(r.getReportReason());
            reportDTO.setAccepted(r.getAccepted());
            reportDTO.setUser(new UserDTO(r.getUser()));
            reportDTO.setComment(new CommentDTO(comment));
            reportDTO.setPost(new PostDTO(r.getPost()));

            reportsDTO.add(reportDTO);
        }

        return new ResponseEntity<>(reportsDTO, HttpStatus.OK);
    }
}
