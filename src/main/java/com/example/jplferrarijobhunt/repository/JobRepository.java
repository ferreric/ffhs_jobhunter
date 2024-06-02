package com.example.jplferrarijobhunt.repository;

import com.example.jplferrarijobhunt.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<JobOffer, Integer> {
    Optional<JobOffer> findById(Integer id);
}