package com.ftn.redditcloneprojekat.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    //private MultipartFile[] files;
}
