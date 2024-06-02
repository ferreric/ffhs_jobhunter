package com.example.jplferrarijobhunt.service;

import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Page<JobOffer> findAllJobs(PageRequest pageRequest) {
        return jobRepository.findAll(pageRequest);
    }

    public Optional<JobOffer> findJobById(Integer id) {
        return jobRepository.findById(id);
    }

    public JobOffer saveJob(JobOffer job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Integer id) {
        jobRepository.deleteById(id);
    }
}
