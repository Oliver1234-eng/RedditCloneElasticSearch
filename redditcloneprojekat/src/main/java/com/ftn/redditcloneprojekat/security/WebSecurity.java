package com.ftn.redditcloneprojekat.security;

import com.ftn.redditcloneprojekat.model.User;
import com.ftn.redditcloneprojekat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class WebSecurity {

    @Autowired
    private UserService userService;

//    public boolean checkClubId(Authentication authentication, HttpServletRequest request, int id) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = userService.findByUsernameNew(userDetails.getUsername());
//        if(id == user.getId()) {
//            return true;
//        }
//        return false;
//    }
}
