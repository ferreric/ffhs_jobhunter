package com.example.jplferrarijobhunt.repository;

import com.example.jplferrarijobhunt.model.ApplicationStatus;
import com.example.jplferrarijobhunt.model.JobOffer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JobRepositoryTests {

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void testSaveAndFindById() {
        JobOffer jobOffer = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        jobRepository.save(jobOffer);

        JobOffer found = jobRepository.findById(jobOffer.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getDescription()).isEqualTo(jobOffer.getDescription());
    }

    @Test
    public void testFindAll() {
        JobOffer job1 = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        JobOffer job2 = new JobOffer("Software Engineer", "www.ictjob.ch", null, ApplicationStatus.APPLIED);
        jobRepository.saveAll(List.of(job1, job2));

        List<JobOffer> jobOffers = jobRepository.findAll();
        assertThat(jobOffers).hasSize(2);
    }
}
