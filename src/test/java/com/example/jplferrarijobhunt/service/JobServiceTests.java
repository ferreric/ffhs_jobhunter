package com.example.jplferrarijobhunt.service;

import com.example.jplferrarijobhunt.model.ApplicationStatus;
import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class JobServiceTests {

    private JobRepository jobRepository;
    private JobService jobService;

    @BeforeEach
    public void setup() {
        jobRepository = Mockito.mock(JobRepository.class);
        jobService = new JobService(jobRepository);
    }

    @Test
    public void testFindAllJobs() {
        JobOffer job1 = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        JobOffer job2 = new JobOffer("Software Engineer", "www.ictjob.ch", null, ApplicationStatus.APPLIED);
        when(jobRepository.findAll()).thenReturn(List.of(job1, job2));

        Page<JobOffer> jobOffers = jobService.findAllJobs(PageRequest.of(0,10));
        assertThat(jobOffers).hasSize(2);
    }

    @Test
    public void testFindJobById() {
        JobOffer jobOffer = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        when(jobRepository.findById(1)).thenReturn(Optional.of(jobOffer));

        Optional<JobOffer> foundJob = jobService.findJobById(1);
        assertThat(foundJob).isPresent();
        assertThat(foundJob.get().getDescription()).isEqualTo("ICT-Praktikant");
    }

    @Test
    public void testSaveJob() {
        JobOffer jobOffer = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        when(jobRepository.save(any(JobOffer.class))).thenReturn(jobOffer);

        JobOffer savedJob = jobService.saveJob(jobOffer);
        assertThat(savedJob).isNotNull();
        assertThat(savedJob.getDescription()).isEqualTo("ICT-Praktikant");
    }

    @Test
    public void testDeleteJob() {
        Mockito.doNothing().when(jobRepository).deleteById(1);

        jobService.deleteJob(1);
        Mockito.verify(jobRepository, Mockito.times(1)).deleteById(1);
    }
}
