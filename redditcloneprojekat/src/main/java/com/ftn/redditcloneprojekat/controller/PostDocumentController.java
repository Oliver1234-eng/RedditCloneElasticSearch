package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.*;
import com.ftn.redditcloneprojekat.model.CommunityDocument;
import com.ftn.redditcloneprojekat.model.PostDocument;
import com.ftn.redditcloneprojekat.service.PostDocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/postDocument")
public class PostDocumentController {

    private final PostDocumentService postDocumentService;

    public PostDocumentController(PostDocumentService postDocumentService) {
        this.postDocumentService = postDocumentService;
    }

    @PostMapping
    public void addPostDocument(@RequestBody PostDocumentDTO postDocumentDTO){
        postDocumentService.index(postDocumentDTO);
    }

    @GetMapping("/reindex")
    public void reindex(){
        postDocumentService.reindex();
    }

    @PostMapping("/title")
    public List<PostDocumentResponseDTO> findPostDocumentsByTitle(@RequestBody PostDocumentTitleDTO postDocumentTitleDTO){
        return postDocumentService.findPostDocumentsByTitle(postDocumentTitleDTO.getTitle());
    }

    @PostMapping("/text")
    public List<PostDocumentResponseDTO> findPostDocumentsByText(@RequestBody PostDocumentTextDTO postDocumentTextDTO){
        return postDocumentService.findPostDocumentsByText(postDocumentTextDTO.getText());
    }

    @PostMapping("/user")
    public List<PostDocumentResponseDTO> findPostDocumentsByUser(@RequestBody PostDocumentUserDTO postDocumentUserDTO){
        return postDocumentService.findPostDocumentsByUser(postDocumentUserDTO.getUser());
    }

    @PostMapping("/flair")
    public List<PostDocumentResponseDTO> findPostDocumentsByFlair(@RequestBody PostDocumentFlairDTO postDocumentFlairDTO){
        return postDocumentService.findPostDocumentsByFlair(postDocumentFlairDTO.getFlair());
    }

    @PostMapping("/community")
    public List<PostDocumentResponseDTO> findPostDocumentsByCommunity(@RequestBody PostDocumentCommunityDTO postDocumentCommunityDTO){
        return postDocumentService.findPostDocumentsByCommunity(postDocumentCommunityDTO.getCommunity());
    }

    @GetMapping("/searchTitle")
    public ResponseEntity<List<PostDocumentResponseDTO>> searchPostsByTitle(@RequestParam("title") String title) {
        List<PostDocumentResponseDTO> postDocuments = postDocumentService.searchPostsByTitle(title);
        return ResponseEntity.ok(postDocuments);
    }

    @GetMapping("/searchText")
    public List<PostDocument> searchPostsByTextPhrase(@RequestParam String searchText) {
        return postDocumentService.searchPostsByTextPhraseService(searchText);
    }

    @GetMapping("/commentCount/{commentCount1}/to/{commentCount2}")
    public List<PostDocumentResponseDTO> findPostDocumentsByCommentCount(@PathVariable int commentCount1, @PathVariable int commentCount2){
        return postDocumentService.findPostDocumentsByCommentCount(commentCount1, commentCount2);
    }

    @GetMapping("/commentCountGreaterThan/{commentCount}")
    public List<PostDocumentResponseDTO> findPostDocumentsByCommentCountGreaterThan(@PathVariable int commentCount){
        return postDocumentService.findPostDocumentsByCommentCountGreaterThan(commentCount);
    }

    @GetMapping("/commentCountLessThan/{commentCount}")
    public List<PostDocumentResponseDTO> findPostDocumentsByCommentCountLessThan(@PathVariable int commentCount){
        return postDocumentService.findPostDocumentsByCommentCountLessThan(commentCount);
    }

    @GetMapping("/karma/{karma1}/to/{karma2}")
    public List<PostDocumentResponseDTO> findPostDocumentsByKarma(@PathVariable int karma1, @PathVariable int karma2){
        return postDocumentService.findPostDocumentsByKarma(karma1, karma2);
    }

    @GetMapping("/karmaGreaterThan/{karma}")
    public List<PostDocumentResponseDTO> findPostDocumentsByKarmaGreaterThan(@PathVariable int karma){
        return postDocumentService.findPostDocumentsByKarmaGreaterThan(karma);
    }

    @GetMapping("/karmaLessThan/{karma}")
    public List<PostDocumentResponseDTO> findPostDocumentsByKarmaLessThan(@PathVariable int karma){
        return postDocumentService.findPostDocumentsByKarmaLessThan(karma);
    }

    @GetMapping("/and/flair/{flair}/user/{user}")
    public List<PostDocumentResponseDTO> findPostDocumentsByFlairAndUser(@PathVariable String flair, @PathVariable String user){
        return postDocumentService.findPostDocumentsByFlairAndUser(flair, user);
    }

    @GetMapping("/or/flair/{flair}/user/{user}")
    public List<PostDocumentResponseDTO> findPostDocumentsByFlairOrUser(@PathVariable String flair, @PathVariable String user){
        return postDocumentService.findPostDocumentsByFlairOrUser(flair, user);
    }

    @PostMapping(path = "/pdf", consumes = {"multipart/form-data"})
    public void uploadPdf(@ModelAttribute PostDocumentDTO uploadModel) throws IOException {
        postDocumentService.indexUploadedFile(uploadModel);
    }
}
