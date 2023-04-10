package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Flair;

public class FlairDTO {

    private Integer id;
    private String name;

    public FlairDTO() {

    }

    public FlairDTO(Flair flair) {
        this(flair.getId(), flair.getName());
    }

    public FlairDTO(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public FlairDTO(Integer id) {
        this.id = id;
    }

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
}
