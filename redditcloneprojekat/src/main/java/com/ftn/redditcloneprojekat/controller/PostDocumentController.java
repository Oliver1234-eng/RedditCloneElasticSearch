package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.PostDocumentDTO;
import com.ftn.redditcloneprojekat.dto.PostDocumentResponseDTO;
import com.ftn.redditcloneprojekat.dto.PostDocumentTextDTO;
import com.ftn.redditcloneprojekat.service.PostDocumentService;
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

    @GetMapping("/title/{title}")
    public List<PostDocumentResponseDTO> findPostDocumentsByTitle(@PathVariable String title){
        return postDocumentService.findPostDocumentsByTitle(title);
    }

    @GetMapping("/text")
    public List<PostDocumentResponseDTO> findPostDocumentsByText(@RequestBody PostDocumentTextDTO postDocumentTextDTO){
        return postDocumentService.findPostDocumentsByText(postDocumentTextDTO.getText());
    }

    @GetMapping("/user/{user}")
    public List<PostDocumentResponseDTO> findPostDocumentsByUser(@PathVariable String user){
        return postDocumentService.findPostDocumentsByUser(user);
    }

    @GetMapping("/flair/{flair}")
    public List<PostDocumentResponseDTO> findPostDocumentsByFlair(@PathVariable String flair){
        return postDocumentService.findPostDocumentsByFlair(flair);
    }

    @GetMapping("/community/{community}")
    public List<PostDocumentResponseDTO> findPostDocumentsByCommunity(@PathVariable String community){
        return postDocumentService.findPostDocumentsByCommunity(community);
    }

    @GetMapping("/commentCount/{commentCount1}/to/{commentCount2}")
    public List<PostDocumentResponseDTO> findPostDocumentsByCommentCount(@PathVariable int commentCount1, @PathVariable int commentCount2){
        return postDocumentService.findPostDocumentsByCommentCount(commentCount1, commentCount2);
    }

    @GetMapping("/karma/{karma1}/to/{karma2}")
    public List<PostDocumentResponseDTO> findPostDocumentsByKarma(@PathVariable int karma1, @PathVariable int karma2){
        return postDocumentService.findPostDocumentsByKarma(karma1, karma2);
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
