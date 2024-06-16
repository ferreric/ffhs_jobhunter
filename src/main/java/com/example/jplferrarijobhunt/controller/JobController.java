package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.service.JobService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/")
    public String home() {
    return "home";
}

    @GetMapping("/jobs")
    public String getAllJobs(Model model, @RequestParam(defaultValue = "0") int page) {
    Page<JobOffer> jobPage = jobService.findAllJobs(PageRequest.of(page, 10)); // pagesize hard coded. to do: make it editable
    for (JobOffer job : jobPage) {
            if (job == null || job.getUrl() == null) {
                throw new IllegalArgumentException("Job or job URL is null");
            }
        }
        model.addAttribute("jobPage", jobPage);
        model.addAttribute("currentPage", page);
        return "joblist";
    }

    @GetMapping("/jobs/create")
    public String showCreateForm(Model model) {
        JobOffer defaultJobOffer = new JobOffer(
                "Job Description",
                "https://www.",
                null,
                null
        );
        model.addAttribute("jobOffer", defaultJobOffer);
        return "jobform";
    }

    @GetMapping("/jobs/{id}")
public ResponseEntity<JobOffer> getJobById(@PathVariable Integer id) {
    Optional<JobOffer> jobOptional = jobService.findJobById(id);
        return jobOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
}

    @PostMapping("/jobs")
    public String createJob(@ModelAttribute JobOffer job) {
        jobService.saveJob(job);
        return "redirect:/jobs";
    }

    @PutMapping("/jobs/{id}")
    @ResponseBody
    public JobOffer updateJob(@PathVariable Integer id, @RequestBody JobOffer updatedJob) {
        Optional<JobOffer> existingJob = jobService.findJobById(id);
        if (existingJob.isPresent()) {
            JobOffer job = existingJob.get();
            job.setAppliedDate(updatedJob.getAppliedDate());
            job.setStatus(updatedJob.getStatus());
            jobService.saveJob(job);
            return job;
        } else {
            throw new EntityNotFoundException("Job not found");
        }
    }

    @DeleteMapping("/jobs/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
public String handleException(Exception e, Model model) {
    model.addAttribute("errorMessage", e.getMessage());
    return "error";
}
}
