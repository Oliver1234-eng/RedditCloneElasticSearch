package com.ftn.redditcloneprojekat.service;

import com.ftn.redditcloneprojekat.model.Reaction;
import com.ftn.redditcloneprojekat.model.Report;
import com.ftn.redditcloneprojekat.repository.ReactionRepository;
import com.ftn.redditcloneprojekat.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public Report findOne(Integer id) {
        return reportRepository.findById(id).orElseGet(null);
    }

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public Page<Report> findAll(Pageable page) {
        return reportRepository.findAll(page);
    }

    public Report save(Report report) {
        return reportRepository.save(report);
    }

    public void remove(Integer id) {
        reportRepository.deleteById(id);
    }
}