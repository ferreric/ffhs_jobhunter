package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<JobOffer> findAllJobs() {
        return jobRepository.findAll();
    }

    public JobOffer findJobById(Integer id) {
        return jobRepository.findById(id).orElse(null);
    }

    public JobOffer saveJob(JobOffer job) {
        return jobRepository.save(job);
    }
}
