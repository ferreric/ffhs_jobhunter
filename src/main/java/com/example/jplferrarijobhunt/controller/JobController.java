package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.JobOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public String getAllJobs(@RequestParam(defaultValue = "0") int page, Model model) {
        Page<JobOffer> jobOffersPage = jobService.findPaginatedJobs(page, 10);
        model.addAttribute("jobOffersPage", jobOffersPage);
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
    public JobOffer getJobById(@PathVariable Integer id) {
        return jobService.findJobById(id);
    }

    @PostMapping
    public String createJob(@ModelAttribute JobOffer job) {
        jobService.saveJob(job);
        return "redirect:/jobs";
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handleException(Exception e) {
        e.printStackTrace();  // Ausgabe des Stacktraces in der Konsole
        return "Error: " + e.getMessage();
    }
}
