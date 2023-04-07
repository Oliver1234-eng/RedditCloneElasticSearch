package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.CommunityDTO;
import com.ftn.redditcloneprojekat.dto.FlairDTO;
import com.ftn.redditcloneprojekat.dto.PostDTO;
import com.ftn.redditcloneprojekat.dto.UserDTO;
import com.ftn.redditcloneprojekat.model.Community;
import com.ftn.redditcloneprojekat.model.Flair;
import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.service.CommunityService;
import com.ftn.redditcloneprojekat.service.FlairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/flairs")
public class FlairController {

    @Autowired
    private FlairService flairService;

    @GetMapping
    public ResponseEntity<List<FlairDTO>> getFlairs() {

        List<Flair> flairs = flairService.findAll();

        List<FlairDTO> flairsDTO = new ArrayList<>();
        for (Flair s : flairs) {
            flairsDTO.add(new FlairDTO(s));
        }

        return new ResponseEntity<>(flairsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<FlairDTO> getFlair(@PathVariable Integer id) {

        Flair flair = flairService.findOne(id);

        if (flair == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new FlairDTO(flair), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<FlairDTO> saveFlair(@RequestBody FlairDTO flairDTO) {

        Flair flair = new Flair();
        flair.setName(flairDTO.getName());

        flair = flairService.save(flair);
        return new ResponseEntity<>(new FlairDTO(flair), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<FlairDTO> updateFlair(@RequestBody FlairDTO flairDTO) {

        Flair flair = flairService.findOne(flairDTO.getId());

        if (flair == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        flair.setName(flairDTO.getName());

        flair = flairService.save(flair);
        return new ResponseEntity<>(new FlairDTO(flair), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteFlair(@PathVariable Integer id) {

        Flair flair = flairService.findOne(id);

        if (flair != null) {
            flairService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{flairId}/posts")
    public ResponseEntity<List<PostDTO>> getFlairPosts(@PathVariable Integer flairId) {

        Flair flair = flairService.findOneWithPosts(flairId);

        Set<Post> posts = flair.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post p : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(p.getId());
            postDTO.setTitle(p.getTitle());
            postDTO.setImagePath(p.getImagePath());
            postDTO.setUser(new UserDTO(p.getUser()));
            postDTO.setFlair(new FlairDTO(flair));
            postDTO.setCommunity(new CommunityDTO(p.getCommunity()));

            postsDTO.add(postDTO);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
}
