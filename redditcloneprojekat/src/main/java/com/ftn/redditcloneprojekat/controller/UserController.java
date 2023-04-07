package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.*;
import com.ftn.redditcloneprojekat.model.*;
import com.ftn.redditcloneprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {

        List<User> users = userService.findAll();

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsersPage(Pageable page) {

        Page<User> users = userService.findAll(page);

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }

        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {

        User user = userService.findOne(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAvatar(userDTO.getAvatar());
        user.setBanned(userDTO.getBanned());

        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

        User user = userService.findOneByUsername(userDTO.getUsername());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setAvatar(userDTO.getAvatar());
        user.setBanned(userDTO.getBanned());

        user = userService.save(user);
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        User user = userService.findOne(id);

        if (user != null) {
            userService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/findUsername")
    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username) {

        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @GetMapping(value = "/findEmail")
    public ResponseEntity<List<UserDTO>> getUsersByEmail(@RequestParam String email) {

        List<User> users = userService.findByEmail(email);

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/email")
    public ResponseEntity<List<UserDTO>> pronadjiKorisnikePoEmailu(@RequestParam String email) {

        List<User> users = userService.pronadjiPoEmailu(email);

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/findUsernamePassword")
    public ResponseEntity<List<UserDTO>> getUsersByUsernameAndPassword(@RequestParam String username,
                                                                       @RequestParam String password) {

        List<User> users = userService.findByUsernameAndPassword(username, password);

        List<UserDTO> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(new UserDTO(u));
        }
        return new ResponseEntity<>(usersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{userUsername}/communities")
    public ResponseEntity<List<CommunityDTO>> getUserCommunities(@PathVariable String userUsername) {

        User user = userService.findOneWithCommunities(userUsername);

        Set<Community> communities = user.getCommunities();
        List<CommunityDTO> communitiesDTO = new ArrayList<>();

        for (Community c: communities) {
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setId(c.getId());
            communityDTO.setName(c.getName());
            communityDTO.setDescription(c.getDescription());
            communityDTO.setRules(c.getRules());
            communityDTO.setSuspended(c.getSuspended());
            communityDTO.setSuspendedReason(c.getSuspendedReason());
            communityDTO.setUser(new UserDTO(c.getUser()));

            communitiesDTO.add(communityDTO);
        }

        return new ResponseEntity<>(communitiesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{userUsername}/reactions")
    public ResponseEntity<List<ReactionDTO>> getUserReactions(@PathVariable String userUsername) {

        User user = userService.findOneWithReactions(userUsername);

        Set<Reaction> reactions = user.getReactions();
        List<ReactionDTO> reactionsDTO = new ArrayList<>();

        for (Reaction r : reactions) {
            ReactionDTO reactionDTO = new ReactionDTO();
            reactionDTO.setId(r.getId());
            reactionDTO.setType(r.getType());
            reactionDTO.setUser(new UserDTO(r.getUser()));
            reactionDTO.setPost(new PostDTO(r.getPost()));
            reactionDTO.setComment(new CommentDTO(r.getComment()));

            reactionsDTO.add(reactionDTO);
        }

        return new ResponseEntity<>(reactionsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{userUsername}/posts")
    public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable String userUsername) {

        User user = userService.findOneWithPosts(userUsername);

        Set<Post> posts = user.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post p : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(p.getId());
            postDTO.setTitle(p.getTitle());
            postDTO.setImagePath(p.getImagePath());
            postDTO.setUser(new UserDTO(p.getUser()));
            postDTO.setFlair(new FlairDTO(p.getFlair()));
            postDTO.setCommunity(new CommunityDTO(p.getCommunity()));

            postsDTO.add(postDTO);
        }

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{userUsername}/comments")
    public ResponseEntity<List<CommentDTO>> getUserComments(@PathVariable String userUsername) {

        User user = userService.findOneWithComments(userUsername);

        Set<Comment> comments = user.getComments();
        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment c : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(c.getId());
            commentDTO.setText(c.getText());
            commentDTO.setDeleted(c.getDeleted());
            commentDTO.setUser(new UserDTO(c.getUser()));
            commentDTO.setPost(new PostDTO(c.getPost()));

            commentsDTO.add(commentDTO);
        }

        return new ResponseEntity<>(commentsDTO, HttpStatus.OK);
    }

//    @GetMapping(value = "/{userId}/communities")
//    public ResponseEntity<List<CommunityDTO>> getUserCommunities(@PathVariable Integer userId) {
//
//        User user = userService.findOneWithCommunities(userId);
//
//        Set<Community> communities = user.getCommunities();
//        List<CommunityDTO> communitiesDTO = new ArrayList<>();
//
//        for (Community c : communities) {
//            CommunityDTO communityDTO = new CommunityDTO();
//            communityDTO.setId(c.getId());
//            communityDTO.setName(c.getName());
//            communityDTO.setDescription(c.getDescription());
//            communityDTO.setCreationDate(c.getCreationDate());
//            communityDTO.setRules(c.getRules());
//            communityDTO.setSuspended(c.getSuspended());
//            communityDTO.setSuspendedReason(c.getSuspendedReason());
//
//            communitiesDTO.add(communityDTO);
//        }
//
//        return new ResponseEntity<>(communitiesDTO, HttpStatus.OK);
//    }

    @GetMapping(value = "/{userUsername}/reports")
    public ResponseEntity<List<ReportDTO>> getUserReports(@PathVariable String userUsername) {

        User user = userService.findOneWithReports(userUsername);

        Set<Report> reports = user.getReports();
        List<ReportDTO> reportsDTO = new ArrayList<>();

        for (Report r : reports) {
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.setId(r.getId());
            reportDTO.setReportReason(r.getReportReason());
            reportDTO.setAccepted(r.getAccepted());
            reportDTO.setUser(new UserDTO(r.getUser()));
            reportDTO.setComment(new CommentDTO(r.getComment()));
            reportDTO.setPost(new PostDTO(r.getPost()));

            reportsDTO.add(reportDTO);
        }

        return new ResponseEntity<>(reportsDTO, HttpStatus.OK);
    }
}
