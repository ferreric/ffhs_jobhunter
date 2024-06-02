package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public String getAllJobs(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<JobOffer> jobPage = jobService.findAllJobs(PageRequest.of(page, 10));
        model.addAttribute("jobPage", jobPage);
        model.addAttribute("currentPage", page);
        return "joblist";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        JobOffer defaultJobOffer = new JobOffer(
                "Job Description",
                "https://www.",
                LocalDate.now(),
                null,
                null
        );
        model.addAttribute("jobOffer", defaultJobOffer);
        return "jobform";
    }

    @GetMapping("/{id}")
    public Optional<JobOffer> getJobById(@PathVariable Integer id) {
        return jobService.findJobById(id);
    }

    @PostMapping
    public String createJob(@ModelAttribute JobOffer job) {
        jobService.saveJob(job);
        return "redirect:/jobs";
    }

    @PutMapping("/{id}")
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
            throw new RuntimeException("Job not found");
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteJob(@PathVariable Integer id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        e.printStackTrace();  // Ausgabe des Stacktraces in der Konsole
        return "Error: " + e.getMessage();
    }
}
