package com.ftn.redditcloneprojekat.dto.mapper;

import com.ftn.redditcloneprojekat.dto.PostDocumentDTO;
import com.ftn.redditcloneprojekat.dto.PostDocumentResponseDTO;
import com.ftn.redditcloneprojekat.model.PostDocument;

public class PostDocumentMapper {

    public static PostDocument mapModel(PostDocumentDTO postDocumentDTO){
        return PostDocument.builder()
                .title(postDocumentDTO.getTitle())
                .text(postDocumentDTO.getText())
                .user(postDocumentDTO.getUser())
                .flair(postDocumentDTO.getFlair())
                .community(postDocumentDTO.getCommunity())
                .commentCount(postDocumentDTO.getCommentCount())
                .karma(postDocumentDTO.getKarma())
                .build();
    }

    public static PostDocumentResponseDTO mapResponseDto(PostDocument postDocument){
        return PostDocumentResponseDTO.builder()
                .id(postDocument.getId())
                .title(postDocument.getTitle())
                .text(postDocument.getText())
                .user(postDocument.getUser())
                .flair(postDocument.getFlair())
                .community(postDocument.getCommunity())
                .commentCount(postDocument.getCommentCount())
                .karma(postDocument.getKarma())
                .build();
    }
}
