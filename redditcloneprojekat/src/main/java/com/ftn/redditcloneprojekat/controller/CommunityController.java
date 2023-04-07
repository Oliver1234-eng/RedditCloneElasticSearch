package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.CommunityDTO;
import com.ftn.redditcloneprojekat.dto.FlairDTO;
import com.ftn.redditcloneprojekat.dto.PostDTO;
import com.ftn.redditcloneprojekat.dto.UserDTO;
import com.ftn.redditcloneprojekat.model.Community;
import com.ftn.redditcloneprojekat.model.Post;
import com.ftn.redditcloneprojekat.model.User;
import com.ftn.redditcloneprojekat.service.CommunityService;
import com.ftn.redditcloneprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/communities")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<CommunityDTO>> getCommunities() {

        List<Community> communities = communityService.findAll();

        List<CommunityDTO> communitiesDTO = new ArrayList<>();
        for (Community s : communities) {
            communitiesDTO.add(new CommunityDTO(s));
        }

        return new ResponseEntity<>(communitiesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommunityDTO> getCommunity(@PathVariable Integer id) {

        Community community = communityService.findOne(id);

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommunityDTO> saveCommunity(@RequestBody CommunityDTO communityDTO) {

        if (communityDTO.getUser() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findOneWithCommunities(communityDTO.getUser().getUsername());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Community community = new Community();
        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setRules(communityDTO.getRules());
        community.setSuspended(communityDTO.getSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());
        community.setUser(user);

        community = communityService.save(community);
        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<CommunityDTO> updateCommunity(@RequestBody CommunityDTO communityDTO) {

        Community community = communityService.findOne(communityDTO.getId());

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setRules(communityDTO.getRules());
        community.setSuspended(communityDTO.getSuspended());
        community.setSuspendedReason(communityDTO.getSuspendedReason());

        community = communityService.save(community);
        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Integer id) {

        Community community = communityService.findOne(id);

        if (community != null) {
            communityService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{communityId}/posts")
    public ResponseEntity<List<PostDTO>> getCommunityPosts(@PathVariable Integer communityId) {

        Community community = communityService.findOneWithPosts(communityId);

        Set<Post> posts = community.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post p : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(p.getId());
            postDTO.setTitle(p.getTitle());
            postDTO.setImagePath(p.getImagePath());
            postDTO.setUser(new UserDTO(p.getUser()));
            postDTO.setFlair(new FlairDTO(p.getFlair()));
            postDTO.setCommunity(new CommunityDTO(community));

            postsDTO.add(postDTO);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
}
