package com.ftn.redditcloneprojekat.dto.mapper;

import com.ftn.redditcloneprojekat.dto.CommentDocumentDTO;
import com.ftn.redditcloneprojekat.dto.CommentDocumentResponseDTO;
import com.ftn.redditcloneprojekat.model.CommentDocument;

public class CommentDocumentMapper {

    public static CommentDocument mapModel(CommentDocumentDTO commentDocumentDTO){
        return CommentDocument.builder()
                .text(commentDocumentDTO.getText())
                .user(commentDocumentDTO.getUser())
                .postId(commentDocumentDTO.getPostId())
                .build();
    }

    public static CommentDocumentResponseDTO mapResponseDto(CommentDocument commentDocument){
        return CommentDocumentResponseDTO.builder()
                .id(commentDocument.getId())
                .text(commentDocument.getText())
                .user(commentDocument.getUser())
                .postId(commentDocument.getPostId())
                .build();
    }
}
