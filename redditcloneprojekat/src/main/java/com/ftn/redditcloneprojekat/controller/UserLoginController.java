package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.UserLoginDTO;
import com.ftn.redditcloneprojekat.model.UserLogin;
import com.ftn.redditcloneprojekat.security.TokenUtils;
import com.ftn.redditcloneprojekat.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/userslogin")
public class UserLoginController {

    @Autowired
    UserLoginService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtils tokenUtils;

    /*
    @Autowired
    public UserController(UserServiceImpl userService, AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService, TokenUtils tokenUtils){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
    }
    */
    @PostMapping()
    public ResponseEntity<UserLoginDTO> create(@RequestBody @Validated UserLoginDTO newUser) {

        UserLogin createdUser = userService.createUser(newUser);

        if(createdUser == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        UserLoginDTO userDTO = new UserLoginDTO(createdUser);

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO userDto) {
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
}
