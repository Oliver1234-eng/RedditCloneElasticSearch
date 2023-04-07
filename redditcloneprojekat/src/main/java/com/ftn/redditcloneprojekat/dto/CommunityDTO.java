package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Community;
import com.ftn.redditcloneprojekat.model.Post;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class CommunityDTO {

    private Integer id;
    private String name;
    private String description;
    private String rules;
    private Boolean isSuspended;
    private String suspendedReason;
    private UserDTO user;

    public CommunityDTO() {

    }

//    public CommunityDTO(Community community) {
//        this(community.getId(), community.getName(), community.getDescription(),
//                community.getCreationDate(), community.getRules(), community.getSuspended(),
//                community.getSuspendedReason());
//    }

//    public CommunityDTO(Integer id, String name, String description, LocalDate creationDate, String rules, Boolean isSuspended, String suspendedReason) {
//        super();
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.creationDate = creationDate;
//        this.rules = rules;
//        this.isSuspended = isSuspended;
//        this.suspendedReason = suspendedReason;
//    }

    public CommunityDTO(Community community) {
        id = community.getId();
        name = community.getName();
        description = community.getDescription();
        rules = community.getRules();
        isSuspended = community.getSuspended();
        user = new UserDTO(community.getUser());
    }

//    public CommunityDTO(Community community) {
//
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public Boolean getSuspended() {
        return isSuspended;
    }

    public void setSuspended(Boolean suspended) {
        isSuspended = suspended;
    }

    public String getSuspendedReason() {
        return suspendedReason;
    }

    public void setSuspendedReason(String suspendedReason) {
        this.suspendedReason = suspendedReason;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
