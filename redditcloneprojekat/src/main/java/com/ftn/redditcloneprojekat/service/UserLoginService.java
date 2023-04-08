package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.dto.UserLoginDTO;
import com.ftn.redditcloneprojekat.model.UserLogin;

public interface UserLoginService {

    UserLogin findByUsername(String username);

    UserLogin createUser(UserLoginDTO userDTO);
}
