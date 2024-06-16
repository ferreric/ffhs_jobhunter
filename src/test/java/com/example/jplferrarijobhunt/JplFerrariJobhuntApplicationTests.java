package com.example.jplferrarijobhunt;

import com.example.jplferrarijobhunt.service.JobService;
import com.example.jplferrarijobhunt.repository.JobRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JplFerrariJobhuntApplicationTests extends AbstractIntegrationTest {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;

    @Test
    void contextLoads() {
        assertNotNull(jobService, "JobService should be initialized");
        assertNotNull(jobRepository, "JobRepository should be initialized");
    }
}