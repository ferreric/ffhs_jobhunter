package com.example.jplferrarijobhunt.repository;

import com.example.jplferrarijobhunt.model.ApplicationStatus;
import com.example.jplferrarijobhunt.model.JobOffer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JobRepositoryTests {

    @Autowired
    private JobRepository jobRepository;

    @Test
    public void testSaveAndFindById() {
        JobOffer jobOffer = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        jobRepository.save(jobOffer);

        JobOffer found = jobRepository.findById(jobOffer.getId()).orElse(null);
        assertThat(found).isNotNull();
    }
}