package com.ftn.redditcloneprojekat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDocumentResponseDTO {

    private String id;
    private String title;
    private String text;
    private String user;
    private String flair;
    private String community;
    private int commentCount;
    private int karma;
}
