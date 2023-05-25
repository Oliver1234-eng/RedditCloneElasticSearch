package com.ftn.redditcloneprojekat.dto;

import com.ftn.redditcloneprojekat.model.Flair;

public class FlairDTO {

    private String name;

    public FlairDTO() {

    }

    public FlairDTO(Flair flair) {
        this(flair.getName());
    }

    public FlairDTO(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
