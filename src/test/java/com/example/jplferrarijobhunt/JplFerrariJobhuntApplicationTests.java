package com.example.jplferrarijobhunt;

import com.example.jplferrarijobhunt.service.JobService;
import com.example.jplferrarijobhunt.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest (classes = JplFerrariJobhuntApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JplFerrariJobhuntApplicationTests extends AbstractIntegrationTest {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        ((AbstractApplicationContext) applicationContext).getBeanFactory().registerDependentBean("dataSource", postgreSQLContainer.getContainerId());
        jobRepository.deleteAllInBatch();
    }

    @Test
    void contextLoads() {
        assertNotNull(jobService, "JobService should be initialized");
    }
}