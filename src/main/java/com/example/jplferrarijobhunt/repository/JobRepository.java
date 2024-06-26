package com.example.jplferrarijobhunt.repository;

import com.example.jplferrarijobhunt.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<JobOffer, Integer> {
    //List<JobOffer> findByUrlAndDate(String url, Date date);
}
