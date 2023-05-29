package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.CommentDocumentDTO;
import com.ftn.redditcloneprojekat.dto.CommentDocumentResponseDTO;
import com.ftn.redditcloneprojekat.dto.CommentDocumentTextDTO;
import com.ftn.redditcloneprojekat.service.CommentDocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/commentDocument")
public class CommentDocumentController {

    private final CommentDocumentService commentDocumentService;

    public CommentDocumentController(CommentDocumentService commentDocumentService) {
        this.commentDocumentService = commentDocumentService;
    }

    @PostMapping
    public void addCommentDocument(@RequestBody CommentDocumentDTO commentDocumentDTO){
        commentDocumentService.index(commentDocumentDTO);
    }

    @PostMapping("/text")
    public List<CommentDocumentResponseDTO> findCommunityDocumentsByText(@RequestBody CommentDocumentTextDTO commentDocumentTextDTO){
        return commentDocumentService.findCommentDocumentsByText(commentDocumentTextDTO.getText());
    }
}
