package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.*;
import com.ftn.redditcloneprojekat.model.*;
import com.ftn.redditcloneprojekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private FlairService flairService;

    @Autowired
    private CommunityService communityService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> getPosts() {

        List<Post> posts = postService.findAll();

        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post p : posts) {
            postsDTO.add(new PostDTO(p));
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Integer id) {

        Post post = postService.findOne(id);

        if (post == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {

        if (postDTO.getUser() == null || postDTO.getFlair() == null || postDTO.getCommunity() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findOneWithPosts(postDTO.getUser().getUsername());
        Flair flair = flairService.findOneWithPosts(postDTO.getFlair().getId());
        Community community = communityService.findOneWithPosts(postDTO.getCommunity().getId());

        if (user == null || flair == null || community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setImagePath(postDTO.getImagePath());
        post.setUser(user);
        post.setFlair(flair);
        post.setCommunity(community);
        user.addPost(post);
        flair.addPost(post);
        community.addPost(post);

        post = postService.save(post);
        return new ResponseEntity<>(new PostDTO(post), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {

        Post post = postService.findOne(postDTO.getId());
        if (post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        post.setImagePath(postDTO.getImagePath());

        post = postService.save(post);
        return new ResponseEntity<>(new PostDTO(post), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {

        Post post = postService.findOne(id);

        if (post != null) {
            postService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{postId}/reactions")
    public ResponseEntity<List<ReactionDTO>> getPostReactions(@PathVariable Integer postId) {

        Post post = postService.findOneWithReactions(postId);

        if (post == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Reaction> reactions = post.getReactions();
        List<ReactionDTO> reactionsDTO = new ArrayList<>();

        for (Reaction r : reactions) {
            ReactionDTO reactionDTO = new ReactionDTO();
            reactionDTO.setId(r.getId());
            reactionDTO.setType(r.getType());
            reactionDTO.setUser(new UserDTO(r.getUser()));
            reactionDTO.setPost(new PostDTO(post));
            reactionDTO.setComment(new CommentDTO(r.getComment()));

            reactionsDTO.add(reactionDTO);
        }

        return new ResponseEntity<>(reactionsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getPostComments(@PathVariable Integer postId) {

        Post post = postService.findOneWithComments(postId);

        if (post == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Comment> comments = post.getComments();
        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment c : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(c.getId());
            commentDTO.setText(c.getText());
            commentDTO.setDeleted(c.getDeleted());
            commentDTO.setUser(new UserDTO(c.getUser()));
            commentDTO.setPost(new PostDTO(post));

            commentsDTO.add(commentDTO);
        }

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{postId}/reports")
    public ResponseEntity<List<ReportDTO>> getPostReports(@PathVariable Integer postId) {

        Post post = postService.findOneWithReports(postId);

        if (post == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Report> reports = post.getReports();
        List<ReportDTO> reportsDTO = new ArrayList<>();

        for (Report r : reports) {
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(r.getId());
            reportDTO.setReportReason(r.getReportReason());
            reportDTO.setAccepted(r.getAccepted());
            reportDTO.setUser(new UserDTO(r.getUser()));
            reportDTO.setComment(new CommentDTO(r.getComment()));
            reportDTO.setPost(new PostDTO(post));

            reportsDTO.add(reportDTO);
        }

        return new ResponseEntity<>(reportsDTO, HttpStatus.OK);
    }

}
