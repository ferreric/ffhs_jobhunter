package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.repository.JobRepository;
//import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/joboffers")
public class OfferController {
    private final JobRepository jobRepository;

    public OfferController() {
        this.jobRepository = new JobRepository();
    }

    @GetMapping
    public List<JobOffer> findAll() {
        return jobRepository.findAll();
    }

    @GetMapping("/{id}")
    public JobOffer findById(@PathVariable Integer id) {
        return jobRepository.findById(id);
    }
}
