package com.ftn.redditcloneprojekat.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class CommunityDocumentDTO {

    private String name;
    private String description;
    private String rules;
    private String suspendedReason;
    private String user;
    private int postCount;
    private int averageKarma;
    private MultipartFile[] files;
}
