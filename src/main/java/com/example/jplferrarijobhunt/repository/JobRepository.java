package com.example.jplferrarijobhunt.repository;
import com.example.jplferrarijobhunt.model.JobOffer;
import com.example.jplferrarijobhunt.model.ApplicationStatus;
import java.time.LocalDate;
import java.util.List;

public class JobRepository {

    private List<JobOffer> offers;

    public JobRepository() {
        offers = List.of(
                new JobOffer(1, "ICT-Praktikant", "www.ictjob.ch", LocalDate.of(2024,5,23), LocalDate.of(2024,5,24), ApplicationStatus.REJECTED),
                new JobOffer(2, "ICT-Praktikant", "www.ictjob.ch", LocalDate.of(2024,5,25), null, ApplicationStatus.APPLIED),
                new JobOffer(3, "ICT-Praktikant", "www.ictjob.ch", LocalDate.now(), null, ApplicationStatus.LISTED)
        );
    }

    public List<JobOffer> findAll() {
        return offers;
    }

    public JobOffer findById(Integer id) {
        return offers
                .stream()
                .filter(a -> a.id().equals(id))
                .findFirst()
                .orElse(null);
    }

}
