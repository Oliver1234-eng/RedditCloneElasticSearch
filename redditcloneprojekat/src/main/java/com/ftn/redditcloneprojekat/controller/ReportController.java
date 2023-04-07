package com.ftn.redditcloneprojekat.controller;

import com.ftn.redditcloneprojekat.dto.CommunityDTO;
import com.ftn.redditcloneprojekat.dto.ReactionDTO;
import com.ftn.redditcloneprojekat.dto.ReportDTO;
import com.ftn.redditcloneprojekat.model.*;
import com.ftn.redditcloneprojekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<List<ReportDTO>> getReports() {

        List<Report> reports = reportService.findAll();

        List<ReportDTO> reportsDTO = new ArrayList<>();
        for (Report r : reports) {
            reportsDTO.add(new ReportDTO(r));
        }

        return new ResponseEntity<>(reportsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ReportDTO> getReport(@PathVariable Integer id) {

        Report report = reportService.findOne(id);

        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new ReportDTO(report), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<ReportDTO> createReport(@RequestBody ReportDTO reportDTO) {

        if (reportDTO.getUser() == null || reportDTO.getComment() == null || reportDTO.getPost() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userService.findOneWithReports(reportDTO.getUser().getUsername());
        Comment comment = commentService.findOneWithReports(reportDTO.getComment().getId());
        Post post = postService.findOneWithReports(reportDTO.getPost().getId());

        if (user == null || comment == null || post == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Report report = new Report();
        report.setReportReason(reportDTO.getReportReason());
        report.setAccepted(reportDTO.getAccepted());
        report.setUser(user);
        report.setComment(comment);
        report.setPost(post);
        post.addReport(report);
        comment.addReport(report);
        user.addReport(report);

        report = reportService.save(report);
        return new ResponseEntity<>(new ReportDTO(report), HttpStatus.CREATED);
    }

    @PutMapping(consumes = "application/json")
    public ResponseEntity<ReportDTO> updateReport(@RequestBody ReportDTO reportDTO) {

        Report report = reportService.findOne(reportDTO.getId());
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        report.setReportReason(reportDTO.getReportReason());
        report.setAccepted(reportDTO.getAccepted());

        report = reportService.save(report);
        return new ResponseEntity<>(new ReportDTO(report), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Integer id) {

        Report report = reportService.findOne(id);

        if (report != null) {
            reportService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
