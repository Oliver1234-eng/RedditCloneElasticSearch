package com.ftn.redditcloneprojekat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommunityDocumentResponseDTO {

    private String id;
    private String name;
    private String description;
    private String rules;
    private String suspendedReason;
    private String user;
    private int postCount;
    private int averageKarma;
}
