package com.example.jplferrarijobhunt.controller;

import com.example.jplferrarijobhunt.model.ApplicationStatus;
import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.service.JobService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(JobController.class)
public class JobControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobService jobService;

    @Autowired
    private ObjectMapper objectMapper;

    private JobOffer job1;
    private JobOffer job2;
    private Page<JobOffer> jobPage;

    @BeforeEach
    public void setup() {
        job1 = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        job2 = new JobOffer("Software Engineer", "www.ictjob.ch", null, ApplicationStatus.APPLIED);
        jobPage = new PageImpl<>(List.of(job1, job2));
    }

    @Test
    public void testGetAllJobs() throws Exception {
        when(jobService.findAllJobs(any(PageRequest.class))).thenReturn(jobPage);

        MvcResult result = mockMvc.perform(get("/jobs").param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(view().name("joblist"))
                .andExpect(model().attributeExists("jobPage"))
                .andExpect(model().attributeExists("currentPage"))
                .andReturn();

        Page<JobOffer> returnedPage = (Page<JobOffer>) result.getModelAndView().getModel().get("jobPage");
        assertThat(returnedPage.getContent()).hasSize(2);
        assertThat(returnedPage.getContent().get(0).getDescription()).isEqualTo("ICT-Praktikant");
    }

    @Test
    public void testGetJobById() throws Exception {
        when(jobService.findJobById(1)).thenReturn(Optional.of(job1));

        MvcResult result = mockMvc.perform(get("/jobs/1"))
                .andExpect(status().isOk())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        JobOffer returnedJob = objectMapper.readValue(jsonResponse, JobOffer.class);
        assertThat(returnedJob.getDescription()).isEqualTo("ICT-Praktikant");
    }

    @Test
    public void testCreateJob() throws Exception {
        JobOffer jobOffer = new JobOffer("ICT-Praktikant", "www.ictjob.ch", null, ApplicationStatus.LISTED);
        when(jobService.saveJob(Mockito.any(JobOffer.class))).thenReturn(jobOffer);

        mockMvc.perform(post("/jobs")
                        .contentType("application/json")
                        .content("{\"description\":\"ICT-Praktikant\",\"url\":\"www.ictjob.ch\",\"addedDate\":\"2024-05-23\",\"appliedDate\":\"2024-05-24\",\"status\":\"APPLIED\"}"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testUpdateJob() throws Exception {
        when(jobService.findJobById(1)).thenReturn(Optional.of(job1));
        when(jobService.saveJob(any(JobOffer.class))).thenReturn(job1);

        mockMvc.perform(put("/jobs/1")
                        .contentType("application/json")
                        .content("{\"appliedDate\":\"2024-05-25\", \"status\":\"APPLIED\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APPLIED"));

        Mockito.verify(jobService, Mockito.times(1)).findJobById(1);
        Mockito.verify(jobService, Mockito.times(1)).saveJob(any(JobOffer.class));
    }

    @Test
    public void testDeleteJob() throws Exception {
        Mockito.doNothing().when(jobService).deleteJob(1);

        mockMvc.perform(delete("/jobs/1"))
                .andExpect(status().isOk());

        Mockito.verify(jobService, Mockito.times(1)).deleteJob(1);
    }
}
