package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.dto.CommentDocumentDTO;
import com.ftn.redditcloneprojekat.dto.CommentDocumentResponseDTO;
import com.ftn.redditcloneprojekat.dto.mapper.CommentDocumentMapper;
import com.ftn.redditcloneprojekat.model.CommentDocument;
import com.ftn.redditcloneprojekat.repository.CommentDocumentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentDocumentService {

    private final CommentDocumentRepository commentDocumentRepository;

    public CommentDocumentService(CommentDocumentRepository commentDocumentRepository) {
        this.commentDocumentRepository = commentDocumentRepository;
    }

    public void index(CommentDocumentDTO commentDocumentDTO){
        commentDocumentRepository.save(CommentDocumentMapper.mapModel(commentDocumentDTO));
    }

    public void index(CommentDocument commentDocument) {
        commentDocumentRepository.save(commentDocument);
    }

    public List<CommentDocumentResponseDTO> findCommentDocumentsByText(String text){
        List<CommentDocument> commentDocuments = commentDocumentRepository.findAllByText(text);
        return mapCommentDocumentsToCommentDocumentsDTO(commentDocuments);
    }

    private List<CommentDocumentResponseDTO> mapCommentDocumentsToCommentDocumentsDTO(List<CommentDocument> commentDocuments){
        List<CommentDocumentResponseDTO> commentDocumentsDTO = new ArrayList<>();
        for(CommentDocument commentDocument : commentDocuments){
            commentDocumentsDTO.add(CommentDocumentMapper.mapResponseDto(commentDocument));
        }
        return commentDocumentsDTO;
    }
}
