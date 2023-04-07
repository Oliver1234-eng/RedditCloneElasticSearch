package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.*;
import com.ftn.redditcloneprojekat.model.*;
import com.ftn.redditcloneprojekat.service.CommentService;
import com.ftn.redditcloneprojekat.service.PostService;
import com.ftn.redditcloneprojekat.service.ReactionService;
import com.ftn.redditcloneprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/reactions")
public class ReactionController {

    @Autowired
    private ReactionService reactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    public ResponseEntity<List<ReactionDTO>> getReactions() {

        List<Reaction> reactions = reactionService.findAll();

        List<ReactionDTO> reactionsDTO = new ArrayList<>();
        for (Reaction r : reactions) {
            reactionsDTO.add(new ReactionDTO(r));
        }

        return new ResponseEntity<>(reactionsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReactionDTO> getReaction(@PathVariable Integer id) {

        Reaction reaction = reactionService.findOne(id);

        if (reaction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ReactionDTO(reaction), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReactionDTO> createReaction(@RequestBody ReactionDTO reactionDTO) {

        if (reactionDTO.getUser() == null || reactionDTO.getPost() == null || reactionDTO.getComment() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findOneWithReactions(reactionDTO.getUser().getUsername());
        Post post = postService.findOneWithReactions(reactionDTO.getPost().getId());
        Comment comment = commentService.findOneWithReactions(reactionDTO.getComment().getId());

        if (user == null || post == null || comment == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Reaction reaction = new Reaction();
        reaction.setType(reactionDTO.getType());
        reaction.setUser(user);
        reaction.setPost(post);
        reaction.setComment(comment);
        post.addReaction(reaction);
        user.addReaction(reaction);
        comment.addReaction(reaction);

        reaction = reactionService.save(reaction);
        return new ResponseEntity<>(new ReactionDTO(reaction), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ReactionDTO> updateReaction(@RequestBody ReactionDTO reactionDTO) {

        Reaction reaction = reactionService.findOne(reactionDTO.getId());
        if (reaction == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reaction.setType(reactionDTO.getType());

        reaction = reactionService.save(reaction);
        return new ResponseEntity<>(new ReactionDTO(reaction), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable Integer id) {

        Reaction reaction = reactionService.findOne(id);

        if (reaction != null) {
            reactionService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
