package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Page<JobOffer> findPaginatedJobs(int page, int size) {
        return jobRepository.findAll(PageRequest.of(page, size));
    }

    public JobOffer findJobById(Integer id) {
        return jobRepository.findById(id).orElse(null);
    }

    public JobOffer saveJob(JobOffer job) {
        return jobRepository.save(job);
    }
}
