package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.User;

public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Boolean isBanned;

    public UserDTO() {

    }

    public UserDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getEmail(),
                user.getAvatar(), user.getBanned());
    }

    public UserDTO(Integer id, String username, String password, String email, String avatar, Boolean isBanned) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.avatar = avatar;
        this.isBanned = isBanned;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getBanned() {
        return isBanned;
    }

    public void setBanned(Boolean banned) {
        isBanned = banned;
    }
}
