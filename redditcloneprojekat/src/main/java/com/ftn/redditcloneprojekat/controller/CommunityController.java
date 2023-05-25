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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping(value = "/{name}")
    public ResponseEntity<CommunityDTO> getCommunity(@PathVariable String name) {

        Community community = communityService.findOne(name);

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CommunityDTO> saveCommunity(@RequestBody CommunityDTO communityDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        User user = userService.findOneWithCommunitiesToken(currentUserName);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Community community = new Community();
        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setRules(communityDTO.getRules());
        community.setSuspendedReason(communityDTO.getSuspendedReason());
        community.setUser(user);

        community = communityService.save(community);
        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.CREATED);
    }

//    @PostMapping(consumes = "application/json")
//    public ResponseEntity<CommunityDTO> saveCommunity(@RequestBody CommunityDTO communityDTO) {
//
//        if (communityDTO.getUser() == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        User user = userService.findOneWithCommunities(communityDTO.getUser().getUsername());
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        Community community = new Community();
//        community.setName(communityDTO.getName());
//        community.setDescription(communityDTO.getDescription());
//        community.setRules(communityDTO.getRules());
//        community.setSuspended(communityDTO.getSuspended());
//        community.setSuspendedReason(communityDTO.getSuspendedReason());
//        community.setUser(user);
//
//        community = communityService.save(community);
//        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.CREATED);
//    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<CommunityDTO> updateCommunity(@RequestBody CommunityDTO communityDTO) {

        Community community = communityService.findOne(communityDTO.getName());

        if (community == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        community.setName(communityDTO.getName());
        community.setDescription(communityDTO.getDescription());
        community.setRules(communityDTO.getRules());
        community.setSuspendedReason(communityDTO.getSuspendedReason());

        community = communityService.save(community);
        return new ResponseEntity<>(new CommunityDTO(community), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Void> deleteCommunity(@PathVariable String name) {

        Community community = communityService.findOne(name);

        if (community != null) {
            communityService.remove(name);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{communityName}/posts")
    public ResponseEntity<List<PostDTO>> getCommunityPosts(@PathVariable String communityName) {

        Community community = communityService.findOneWithPosts(communityName);

        if (community == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Post> posts = community.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post p : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(p.getId());
            postDTO.setTitle(p.getTitle());
            postDTO.setUser(new UserDTO(p.getUser()));
            postDTO.setFlair(new FlairDTO(p.getFlair()));
            postDTO.setCommunity(new CommunityDTO(community));

            postsDTO.add(postDTO);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }
}
