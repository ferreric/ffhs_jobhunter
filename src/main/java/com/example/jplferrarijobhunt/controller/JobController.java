package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.controller.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<JobOffer> getAllJobs() {
        return jobService.findAllJobs();
    }

    @GetMapping("/{id}")
    public JobOffer getJobById(@PathVariable Integer id) {
        return jobService.findJobById(id);
    }

    @PostMapping
    public JobOffer createJob(@RequestBody JobOffer job) {
        return jobService.saveJob(job);
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        e.printStackTrace();  // Ausgabe des Stacktraces in der Konsole
        return "Error: " + e.getMessage();
    }
}
