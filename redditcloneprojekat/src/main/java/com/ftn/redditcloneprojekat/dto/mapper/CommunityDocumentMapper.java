package com.ftn.redditcloneprojekat.dto.mapper;

import com.ftn.redditcloneprojekat.dto.CommunityDocumentDTO;
import com.ftn.redditcloneprojekat.dto.CommunityDocumentResponseDTO;
import com.ftn.redditcloneprojekat.model.CommunityDocument;

public class CommunityDocumentMapper {

    public static CommunityDocument mapModel(CommunityDocumentDTO communityDocumentDTO){
        return CommunityDocument.builder()
                .name(communityDocumentDTO.getName())
                .description(communityDocumentDTO.getDescription())
                .rules(communityDocumentDTO.getRules())
                .suspendedReason(communityDocumentDTO.getSuspendedReason())
                .user(communityDocumentDTO.getUser())
                .postCount(communityDocumentDTO.getPostCount())
                .averageKarma(communityDocumentDTO.getAverageKarma())
                .build();
    }

    public static CommunityDocumentResponseDTO mapResponseDto(CommunityDocument communityDocument){
        return CommunityDocumentResponseDTO.builder()
                .id(communityDocument.getId())
                .name(communityDocument.getName())
                .description(communityDocument.getDescription())
                .rules(communityDocument.getRules())
                .suspendedReason(communityDocument.getSuspendedReason())
                .user(communityDocument.getUser())
                .postCount(communityDocument.getPostCount())
                .averageKarma(communityDocument.getAverageKarma())
                .build();
    }
}
