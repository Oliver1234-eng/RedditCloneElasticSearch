package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.*;
import com.ftn.redditcloneprojekat.model.CommunityDocument;
import com.ftn.redditcloneprojekat.service.CommunityDocumentService;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/name")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByName(@RequestBody CommunityDocumentNameDTO communityDocumentNameDTO){
        return communityDocumentService.findCommunityDocumentsByName(communityDocumentNameDTO.getName());
    }

    @PostMapping("/description")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByDescription(@RequestBody CommunityDocumentDescriptionDTO communityDocumentDescriptionDTO){
        return communityDocumentService.findCommunityDocumentsByDescription(communityDocumentDescriptionDTO.getDescription());
    }

    @PostMapping("/rules")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByRules(@RequestBody CommunityDocumentRulesDTO communityDocumentRulesDTO){
        return communityDocumentService.findCommunityDocumentsByRules(communityDocumentRulesDTO.getRules());
    }

    @PostMapping("/suspendedReason")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsBySuspendedReason(@RequestBody CommunityDocumentSuspendedReasonDTO communityDocumentSuspendedReasonDTO){
        return communityDocumentService.findCommunityDocumentsBySuspendedReason(communityDocumentSuspendedReasonDTO.getSuspendedReason());
    }

    @PostMapping("/user")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByUser(@RequestBody CommunityDocumentUserDTO communityDocumentUserDTO){
        return communityDocumentService.findCommunityDocumentsByUser(communityDocumentUserDTO.getUser());
    }

    @GetMapping("/searchName")
    public ResponseEntity<List<CommunityDocumentResponseDTO>> searchCommunitiesByName(@RequestParam("name") String name) {
        List<CommunityDocumentResponseDTO> communityDocuments = communityDocumentService.searchCommunitiesByName(name);
        return ResponseEntity.ok(communityDocuments);
    }

    @GetMapping("/searchDescription")
    public List<CommunityDocument> searchCommunitiesByDescriptionPhrase(@RequestParam String searchDescription) {
        return communityDocumentService.searchCommunitiesByDescriptionPhraseService(searchDescription);
    }

    @GetMapping("/postCount/{postCount1}/to/{postCount2}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByPostCount(@PathVariable int postCount1, @PathVariable int postCount2){
        return communityDocumentService.findCommunityDocumentsByPostCount(postCount1, postCount2);
    }

    @GetMapping("/postCountGreaterThan/{postCount}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByPostCountGreaterThan(@PathVariable int postCount){
        return communityDocumentService.findCommunityDocumentsByPostCountGreaterThan(postCount);
    }

    @GetMapping("/postCountLessThan/{postCount}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByPostCountLessThan(@PathVariable int postCount){
        return communityDocumentService.findCommunityDocumentsByPostCountLessThan(postCount);
    }

    @GetMapping("/averageKarma/{averageKarma1}/to/{averageKarma2}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByAverageKarma(@PathVariable int averageKarma1, @PathVariable int averageKarma2){
        return communityDocumentService.findCommunityDocumentsByAverageKarma(averageKarma1, averageKarma2);
    }

    @GetMapping("/averageKarmaGreaterThan/{averageKarma}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByAverageKarmaGreaterThan(@PathVariable int averageKarma){
        return communityDocumentService.findCommunityDocumentsByAverageKarmaGreaterThan(averageKarma);
    }

    @GetMapping("/averageKarmaLessThan/{averageKarma}")
    public List<CommunityDocumentResponseDTO> findCommunityDocumentsByAverageKarmaLessThan(@PathVariable int averageKarma){
        return communityDocumentService.findCommunityDocumentsByAverageKarmaLessThan(averageKarma);
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
