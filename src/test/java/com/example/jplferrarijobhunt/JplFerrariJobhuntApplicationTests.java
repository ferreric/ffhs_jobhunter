package com.example.jplferrarijobhunt;

import com.example.jplferrarijobhunt.service.JobService;
import com.example.jplferrarijobhunt.repository.JobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
//@Testcontainers
class JplFerrariJobhuntApplicationTests {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobRepository jobRepository;
//
//    @Container
//    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16-alpine")
//            .withDatabaseName("jobhunter")
//            .withUsername("jobhunter")
//            .withPassword("admin")
//            .withInitScript("db-init/init.sql");
//
//    @DynamicPropertySource
//    static void configureProperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
//        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
//        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
//    }

    @BeforeEach
    void setUp() {
        jobRepository.deleteAllInBatch();
    }

    @Test
    void contextLoads() {
        assertNotNull(jobService, "JobService should be initialized");
        assertNotNull(jobRepository, "JobRepository should be initialized");
    }
}