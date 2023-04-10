package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.ReactionOnPostDTO;
import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.ReactionOnPost;
import com.ftn.redditcloneprojekat.model.ReactionType;
import com.ftn.redditcloneprojekat.model.User;
import com.ftn.redditcloneprojekat.service.PostService;
import com.ftn.redditcloneprojekat.service.ReactionOnPostService;
import com.ftn.redditcloneprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reactionsOnPost")
public class ReactionOnPostController {

    @Autowired
    private ReactionOnPostService reactionOnPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<ReactionOnPostDTO>> getReactions() {

        List<ReactionOnPost> reactions = reactionOnPostService.findAll();

        List<ReactionOnPostDTO> reactionsDTO = new ArrayList<>();
        for (ReactionOnPost r : reactions) {
            reactionsDTO.add(new ReactionOnPostDTO(r));
        }

        return new ResponseEntity<>(reactionsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReactionOnPostDTO> getReaction(@PathVariable Integer id) {

        ReactionOnPost reactionOnPost = reactionOnPostService.findOne(id);

        if (reactionOnPost == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ReactionOnPostDTO(reactionOnPost), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReactionOnPostDTO> createReaction(@RequestBody ReactionOnPostDTO reactionOnPostDTO) {

        if (reactionOnPostDTO.getPost() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userService.findOneWithReactionsOnPostToken(currentUserName);
        Post post = postService.findOneWithReactionsOnPost(reactionOnPostDTO.getPost().getId());

        if (post == null || user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        ReactionOnPost reactionOnPost = new ReactionOnPost();
        reactionOnPost.setReactionType(reactionOnPostDTO.getReactionType());
        reactionOnPost.setUser(user);
        reactionOnPost.setPost(post);
        user.addReactionOnPost(reactionOnPost);
        post.addReactionOnPost(reactionOnPost);

        reactionOnPost = reactionOnPostService.save(reactionOnPost);
        return new ResponseEntity<>(new ReactionOnPostDTO(reactionOnPost), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ReactionOnPostDTO> updateReaction(@RequestBody ReactionOnPostDTO reactionDTO) {

        ReactionOnPost reaction = reactionOnPostService.findOne(reactionDTO.getId());
        if (reaction == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        reaction.setReactionType(reactionDTO.getReactionType());

        reaction = reactionOnPostService.save(reaction);
        return new ResponseEntity<>(new ReactionOnPostDTO(reaction), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReaction(@PathVariable Integer id) {

        ReactionOnPost reaction = reactionOnPostService.findOne(id);

        if (reaction != null) {
            reactionOnPostService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
