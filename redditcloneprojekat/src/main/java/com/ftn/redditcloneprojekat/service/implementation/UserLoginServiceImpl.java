package com.ftn.redditcloneprojekat.service.implementation;

import com.ftn.redditcloneprojekat.dto.UserLoginDTO;
import com.ftn.redditcloneprojekat.model.Roles;
import com.ftn.redditcloneprojekat.model.UserLogin;
import com.ftn.redditcloneprojekat.repository.UserLoginRepository;
import com.ftn.redditcloneprojekat.security.SecurityProperties;
import com.ftn.redditcloneprojekat.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private UserLoginRepository userRepository;

    @Autowired
    private SecurityProperties securityProperties;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    /*
    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
*/
    @Override
    public UserLogin findByUsername(String username) {
        Optional<UserLogin> user = userRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    @Override
    public UserLogin createUser(UserLoginDTO userDTO) {

        Optional<UserLogin> user = userRepository.findFirstByUsername(userDTO.getUsername());

        if(user.isPresent()){
            return null;
        }

        UserLogin newUser = new UserLogin();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(securityProperties.createPasswordEncoder().encode(userDTO.getPassword()));
        newUser.setRole(Roles.USER);
        newUser = userRepository.save(newUser);

        return newUser;
    }
}
