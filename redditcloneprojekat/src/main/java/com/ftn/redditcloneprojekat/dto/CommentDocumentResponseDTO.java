package com.ftn.redditcloneprojekat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDocumentResponseDTO {

    private String id;
    private String text;
    private String user;
    private int postId;
}
