package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.CommunityDocumentDTO;
import com.ftn.redditcloneprojekat.dto.CommunityDocumentDescriptionDTO;
import com.ftn.redditcloneprojekat.dto.CommunityDocumentResponseDTO;
import com.ftn.redditcloneprojekat.service.CommunityDocumentService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/communityDocument")
public class CommunityDocumentController {

    private final CommunityDocumentService communityDocumentService;

    public CommunityDocumentController(CommunityDocumentService communityDocumentService) {
        this.communityDocumentService = communityDocumentService;
    }

    @PostMapping
    public void addCommunityDocument(@RequestBody CommunityDocumentDTO communityDocumentDTO){
        communityDocumentService.index(communityDocumentDTO);
    }

    @GetMapping("/reindex")
    public void reindex(){
        communityDocumentService.reindex();
    }

    @GetMapping("/name/{name}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByName(@PathVariable String name){
        return communityDocumentService.findCommunityDocumentsByName(name);
    }

    @GetMapping("/description")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByDescription(@RequestBody CommunityDocumentDescriptionDTO communityDocumentDescriptionDTO){
        return communityDocumentService.findCommunityDocumentsByDescription(communityDocumentDescriptionDTO.getDescription());
    }

    @GetMapping("/rules/{rules}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByRules(@PathVariable String rules){
        return communityDocumentService.findCommunityDocumentsByRules(rules);
    }

    @GetMapping("/suspendedReason/{suspendedReason}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsBySuspendedReason(@PathVariable String suspendedReason){
        return communityDocumentService.findCommunityDocumentsBySuspendedReason(suspendedReason);
    }

    @GetMapping("/user/{user}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByUser(@PathVariable String user){
        return communityDocumentService.findCommunityDocumentsByUser(user);
    }

    @GetMapping("/postCount/{postCount1}/to/{postCount2}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByPostCount(@PathVariable int postCount1, @PathVariable int postCount2){
        return communityDocumentService.findCommunityDocumentsByPostCount(postCount1, postCount2);
    }

    @GetMapping("/averageKarma/{averageKarma1}/to/{averageKarma2}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByAverageKarma(@PathVariable int averageKarma1, @PathVariable int averageKarma2){
        return communityDocumentService.findCommunityDocumentsByAverageKarma(averageKarma1, averageKarma2);
    }

    @GetMapping("/and/name/{name}/user/{user}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByNameAndUser(@PathVariable String name, @PathVariable String user){
        return communityDocumentService.findCommunityDocumentsByNameAndUser(name, user);
    }

    @GetMapping("/or/name/{name}/user/{user}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByNameOrUser(@PathVariable String name, @PathVariable String user){
        return communityDocumentService.findCommunityDocumentsByNameOrUser(name, user);
    }

    @PostMapping(path = "/pdf", consumes = {"multipart/form-data"})
    public void uploadPdf(@ModelAttribute CommunityDocumentDTO uploadModel) throws IOException {
        communityDocumentService.indexUploadedFile(uploadModel);
    }
}
