package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.User;

import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String email;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this(user.getUsername(), user.getPassword(), user.getEmail());
    }

    public UserDTO(String username, String password, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
