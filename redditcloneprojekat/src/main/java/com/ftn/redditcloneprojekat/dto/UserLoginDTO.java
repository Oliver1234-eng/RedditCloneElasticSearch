package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.UserLogin;

import javax.validation.constraints.NotBlank;

public class UserLoginDTO {

    private Long id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public UserLoginDTO() {

    }

    public UserLoginDTO(UserLogin createdUser) {
        this.id = createdUser.getId();
        this.username = createdUser.getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
