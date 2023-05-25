package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.*;
import com.ftn.redditcloneprojekat.mapper.UserMapper;
import com.ftn.redditcloneprojekat.model.*;
import com.ftn.redditcloneprojekat.security.SecurityProperties;
import com.ftn.redditcloneprojekat.security.TokenUtils;
import com.ftn.redditcloneprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping(value = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

//    @Autowired
//    UserMapper userMapper;

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

//    @GetMapping(value = "/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathVariable Integer id) {
//
//        User user = userService.findOne(id);
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
//    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {

        User user = userService.findOneByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Validated UserDTO newUser) {

        User createdUser = userService.createUser(newUser);

        if(createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserDTO userDTO = new UserDTO(createdUser);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

//    @PostMapping(consumes = "application/json")
//    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
//
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(securityProperties.createPasswordEncoder().encode(userDTO.getPassword()));
//        //user.setPassword(userDTO.getPassword());
//        user.setEmail(userDTO.getEmail());
//        user.setAvatar(userDTO.getAvatar());
//        user.setBanned(userDTO.getBanned());
//
//        user = userService.save(user);
//        return new ResponseEntity<>(new UserDTO(user), HttpStatus.CREATED);
//    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

        User user = userService.findOneByUsername(userDTO.getUsername());

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setUsername(userDTO.getUsername());
        user.setPassword(securityProperties.createPasswordEncoder().encode(userDTO.getPassword()));
        //user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());

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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getUsername());
            return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeDTO passwordChangeDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        UserDTO userDTO = userService.changePassword(currentUserName, passwordChangeDTO.getOldPassword(), passwordChangeDTO.getNewPassword());

        if (userDTO != null) {
            return ResponseEntity.ok("Password changed successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while changing password.");
        }
    }

//    @GetMapping(value = "/findUsername")
//    public ResponseEntity<UserDTO> getUserByUsername(@RequestParam String username) {
//
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(new UserDTO(user), HttpStatus.OK);
//    }

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

        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Community> communities = user.getCommunities();
        List<CommunityDTO> communitiesDTO = new ArrayList<>();

        for (Community c: communities) {
            CommunityDTO communityDTO = new CommunityDTO();
            communityDTO.setName(c.getName());
            communityDTO.setDescription(c.getDescription());
            communityDTO.setRules(c.getRules());
            communityDTO.setSuspendedReason(c.getSuspendedReason());
            communityDTO.setUser(new UserDTO(c.getUser()));

            communitiesDTO.add(communityDTO);
        }

        return new ResponseEntity<>(communitiesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{userUsername}/reactions")
    public ResponseEntity<List<ReactionDTO>> getUserReactions(@PathVariable String userUsername) {

        User user = userService.findOneWithReactions(userUsername);

        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

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

    @GetMapping(value = "/{userUsername}/reactionsOnPost")
    public ResponseEntity<List<ReactionOnPostDTO>> getUserReactionsOnPost(@PathVariable String userUsername) {

        User user = userService.findOneWithReactionsOnPost(userUsername);

        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<ReactionOnPost> reactions = user.getReactionsOnPost();
        List<ReactionOnPostDTO> reactionsDTO = new ArrayList<>();

        for (ReactionOnPost r : reactions) {
            ReactionOnPostDTO reactionDTO = new ReactionOnPostDTO();
            reactionDTO.setId(r.getId());
            reactionDTO.setReactionType(r.getReactionType());
            reactionDTO.setUser(new UserDTO(r.getUser()));
            reactionDTO.setPost(new PostDTO(r.getPost()));

            reactionsDTO.add(reactionDTO);
        }

        return new ResponseEntity<>(reactionsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{userUsername}/posts")
    public ResponseEntity<List<PostDTO>> getUserPosts(@PathVariable String userUsername) {

        User user = userService.findOneWithPosts(userUsername);

        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Post> posts = user.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for (Post p : posts) {
            PostDTO postDTO = new PostDTO();
            postDTO.setId(p.getId());
            postDTO.setTitle(p.getTitle());
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

        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        Set<Comment> comments = user.getComments();
        List<CommentDTO> commentsDTO = new ArrayList<>();

        for (Comment c : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(c.getId());
            commentDTO.setText(c.getText());
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

        if (user == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

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
